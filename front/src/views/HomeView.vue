<script>
export default {
  setup() {
    console.log('hi mom')
  }
}
</script>

<template>
  <main>
    <div class="board">
      <div class="board-inner">
        <div class="board-cell" v-for="i in 24 * 24" :key="i" :index = "i" />
      </div>
      <img class="board-bg" src="@/assets/board.webp"  alt="board"/>
    </div>
  </main>
</template>

<style lang="scss" scoped>

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

  &-cell {
	  background-color: transparent;

	  &:hover {
			background-color: rgba(240, 60, 80, .5);
		}
  }

  &-bg {
	  z-index: -1;
		position: absolute;
	  height: inherit;
	  inset: 0;
  }
}
</style>
