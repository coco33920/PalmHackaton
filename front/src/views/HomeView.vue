<script>
export default {
  setup() {
    console.log('hi mom')
  }
}
</script>

<template>
  <main>
    <div class="board-wrapper">
      <div class="board">
        <div class="board-line" v-for="i in 24 * 24" :key="i" :index = "i" />
      </div>
      <img id="board-bg" src="@/assets/board.webp" />
    </div>
  </main>
</template>

<style scoped>
#board-bg {
  height: 99vh;
  aspect-ratio: 1/1;
}
</style>