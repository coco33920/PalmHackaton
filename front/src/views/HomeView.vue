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

<template>
	<main>
		<div class="board">
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
	</main>
</template>

<style lang="scss" scoped>
.active {
	background-color: rgba(240, 60, 80, .5);
}

.board {
	height : 100vmin;
	aspect-ratio: 1/1;

	&-inner {
		box-sizing: border-box;
		padding: 5.172vmin;
		display: grid;
		grid-template-columns: repeat(24, 1fr);

		aspect-ratio: inherit;
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
</style>
