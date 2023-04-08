<script setup>
const board_size = 24;
let active_cells = [];

const invalid_cell_ids = [
	0, 1, 2, 3, 4, 5, 6, 7,
	16, 17, 18, 19, 20, 21, 22, 23,
	96, 120, 144, 167,
	384, 408, 432, 456, 480, 504, 528,
	407, 431, 455, 479, 503, 527, 551,
	552, 553, 554, 555, 556, 557, 558, 559,
	568, 569, 570, 571, 572, 573, 574, 575
];

const rooms = [
	{x: 0, y: 1, width: 9, height: 3}, // Rue de la soif
	{x: 17, y: 1, width: 7, height: 5}, // Temple OS
	{x: 0, y: 8, width: 5, height: 6}, // Amphi P
	{x: 9, y: 8, width: 6, height: 7}, // Foyer
	{x: 19, y: 8, width: 5, height: 7}, // E303
	{x: 1, y: 17, width: 5, height: 6}, // Black hole
	{x: 8, y: 20, width: 8, height: 4}, // i59
	{x: 17, y: 18, width: 6, height: 5}, // Rennes 2
];

function handle_mouse_over(cell_id) {
	const room = get_room_from_cell(cell_id);

	active_cells = [];
	if (!room) {
		if (!invalid_cell_ids.includes(cell_id)) {
			active_cells.push(cell_id);
		}
		update_active_cells();
		return;
	}
	for (let x = room.x; x < room.x + room.width; x++) {
		for (let y = room.y; y < room.y + room.height; y++) {
			const cell_id = y * board_size + x;
			active_cells.push(cell_id);
		}
	}
	update_active_cells();
}

function is_active(cell_id) {
	return active_cells.includes(cell_id);
}

function get_room_from_cell(cell_id) {
	const cell_x = cell_id % board_size;
	const cell_y = Math.floor(cell_id / board_size);

	for (let i = 0; i < rooms.length; i++) {
		const room = rooms[i];

		if (
			cell_x >= room.x
			&& cell_x < room.x + room.width
			&& cell_y >= room.y
			&& cell_y < room.y + room.height
		) {
			return room;
		}
	}
	return null;
}

function update_active_cells() {
	const cells = document.querySelectorAll(".board-cell");

	cells.forEach((cell) => {
		const cell_id = parseInt(cell.getAttribute("index"));

		if (active_cells.includes(cell_id)) {
			cell.classList.add("active");
		} else {
			cell.classList.remove("active");
		}
	});
}
</script>

<script>
import axios from "axios";

export default {
	data() {
		return {
			uuid: 0,
			players: undefined,
			board_size: 24,
			turn: 0,
			roll: undefined,
			assets: {
				0: "suspect_bacoin_0.png",
				1: "suspect_dog_1.png",
				2: "suspect_ridoux_2.png",
				3: "suspect_doctor_3.png",
				4: "suspect_palme_4.png",
				5: "suspect_ocaml_5.png",
				10: "arme_bec_bunsen_10.png",
				11: "arme_broyeur_11.png",
				12: "arme_rm_12.png",
				13: "arme_gribouillage_13.png",
				14: "arme_coffee_14.png",
				15: "arme_win_update_15.png",
				16: "arme_hammer_16.png",
				17: "arme_katana_17.png",
				20: "place_soif_20.png",
				21: "place_amphi_21.png",
				22: "place_black_hole_22.png",
				24: "place_i59_24.png",
				25: "place_temple_os_25.png",
				26: "place_e303_26.png",
				27: "place_rennes_2_27.png",
			}
		};
	},
	methods: {
		get_turn_player() {
			if (this.players !== undefined)
				return this.players[this.turn % this.players.length];
			return {
				name: "Loading",
				deck: [],
				roll: undefined
			}
		},
		setup_game_state() {
			axios.get(
				`http://localhost:8080/game_state/${this.uuid}`
			).then(
				(res) => {
					this.players = res.data;
					console.log(res.data);
					this.setup_players(res.data);
				}
			)
		},
		setup_players(players) {
			players.forEach((player) => {
				const cells = document.querySelectorAll(".board-cell");
				let target_cell_id = player.posY * this.board_size + player.posX;

				cells.forEach((cell) => {
					const cell_id = parseInt(cell.getAttribute("index"));

					if (cell_id === target_cell_id) {
							cell.classList.add(`player_${player.hexColor}`);
							cell.classList.add("player");
						}
					})
			})
		},
		next_player() {
			this.turn++;
		},
		roll_dice() {
			this.roll = Math.floor(Math.random() * 6) + 1;
		}
	},
	beforeCreate() {
		axios.get(
			"http://localhost:8080/request_new_game"
		).then(
			(res) => {
				this.uuid = res.data.uuid;
				this.setup_game_state();
			}
		);
	}
}
</script>

<template>
	<main>
		<div id="board" class="board">
			<div class="board-inner">
				<div
					:class="{ active: is_active(i) }"
					class="board-cell"
					v-for="i in 24 * 24" :key="i"
					:index = "i - 1"
					@mouseover="handle_mouse_over(i - 1);"
				/>
			</div>
			<img class="board-bg" src="@/assets/board.webp"  alt="board"/>
		</div>
		<div class="ui">
			<p class="ui-text ui-player_name">Tour de {{ this.get_turn_player().name }}</p>
			<div class="ui-buttons">
				<button id="ui-btn" @click="next_player()">Next Turn</button>
				<button id="ui-btn" @click="roll_dice()">🎲</button>
				<p class="ui-text" v-if="this.roll !== undefined">{{ this.roll }}</p>
			</div>

			<div class="ui-cards">
				<div class="card" v-for="card in get_turn_player().deck">
					<img :src="'src/assets/' + this.assets[card]" alt="">
				</div>
			</div>
		</div>
	</main>
</template>

<style lang="scss">
.active {
	background-color: rgba(240, 60, 80, .5);
}

.board {
	position: relative;
	height : 75vmin;
	aspect-ratio: 1/1;

	&-inner {
		box-sizing: border-box;
		padding: calc(5.172vmin * .75);
		display: grid;
		grid-template-columns: repeat(24, 1fr);

		aspect-ratio: inherit;
		font-weight: bold;
		font-size: 2vmin;
		height: inherit;
		position: relative;
		isolation: isolate;
	}

  &-bg {
	  z-index: -1;
		position: absolute;
	  height: inherit;
	  inset: 0;
  }
}

.player {
	position: relative;

	&::after {
		content: '';
		inset: 0;
		position: absolute;

		box-sizing: border-box;
		margin: .8vmin;

		display: grid;
		place-content: center;

		color: white;
		border: .2vmin solid black;
		border-radius: 50em;
	}

	&_fc5e5e { background-color: #fc5e5e; }
	&_e668f1 { background-color: #e668f1; }
	&_7b86e5 { background-color: #7b86e5; }

	&_86fa83 { background-color: #86fa83; }
	&_7be5df { background-color: #7be5df; }
	&_e5e17b { background-color: #e5e17b; }
}

@keyframes fadeIn {
	0% {
		transform: translateY(0);
	}

	99% {
		transform: translateY(120%) scale(1);
	}

	100% {
		transform: translateY(120%) scale(0);
	}
}

#app {
	color: white;
	margin: 1em;
}

body {
	position: relative;
	background-image: url("src/assets/bg.png");

	&::after {
		content: "Qui a détruit le mémoire d'Hector?";
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;

		font-size: 3vmin;
		font-weight: bold;
		font-family: sans-serif;

		color: white;
		background: black;

		position: absolute;

		inset: -1vmin;
		overflow: hidden;

		animation: fadeIn 1s ease-in-out 2s forwards;
		z-index: 999;
	}
}

main {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.ui {
	min-width: 320px;
	padding: 1vmin;

	&-buttons {
		display: flex;
		align-items: center;
	}

	&-cards {
		margin: 1vmin;
		display: flex;
		gap: 1em;
	}

	&-text {
		margin: 0;
		padding: 1vmin;
		font-family: sans-serif;
		font-weight: bold;
		font-size: 2vmin;
	}
}

#ui-btn {
	font-family: sans-serif;
	font-weight: bold;
	font-size: 2vmin;
	padding: 1vmin;

	background: #E5B47B;
	border: 2px solid #CC845C;
	border-radius: 1vmin;
	margin: 1vmin;
}

@media (orientation: landscape)
{
	main {
		flex-direction: row;
	}
	.ui-cards {
		flex-direction: column;
	}
}

@media (orientation: portrait) {
	main {
		flex-direction: column;
	}

	.ui-cards {
		flex-direction: row;
	}
}

.card > img {
	height: 20vmin;
}
</style>
