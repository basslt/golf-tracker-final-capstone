<template>
  <div class="player-score">
    <p>{{ player.username }}</p>
    <input type="number" v-model="player.score" />
  </div>
</template>

// <script>
// export default {
//   props: {
//     player: {
//       type: Object,
//       required: true
//     }
//   }
// };
// </script>

<style>
.player-score {
  margin-bottom: 5px;
}
</style>
