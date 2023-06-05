<template>
  <div>
    <h2>Log Score</h2>
    <form @submit.prevent="logScore">
      <label for="score">Score:</label>
      <input type="number" v-model="score" required>
      <br>
      <button type="submit">Log Score</button>
    </form>
  </div>
</template>

<script>
import ScoreService from '@/services/ScoreService';
import MatchService from '@/services/MatchService';
import MatchPlayerService from '@/services/MatchPlayerService';

export default {
  props: {
    leagueId: {
      type: Number,
      required: true
    },
    userId: {
      type: Number,
      required: true
    },
    matchId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      score: null
    };
  },
  mounted() {
    
  },
  watch: {
    
  },
  methods: {
    logScore() {
      const { userId, matchId, score } = this;

      MatchService.getMatchById(matchId)
        .then(match => {
          const { leagueId } = match;

          ScoreService.createScore({ playerId: userId, matchId, score })
            .then(() => {
              MatchPlayerService.getMatchPlayersByMatch(matchId)
                .then(matchPlayers => {
                  const matchPlayer = matchPlayers.find(mp => mp.playerId === userId);
                  if (matchPlayer) {
                    matchPlayer.score = score;
                    MatchPlayerService.updateMatchPlayer(matchPlayer.id, matchPlayer)
                      .then(() => {
                        console.log('Score logged and match player updated');
                       
                      })
                      .catch(error => {
                        console.error('Failed to update match player:', error);
                      });
                  } else {
                    console.error('Match player not found');
                  }
                })
                .catch(error => {
                  console.error('Failed to fetch match players:', error);
                });
            })
            .catch(error => {
              console.error('Failed to log score:', error);
            });
        })
        .catch(error => {
          console.error('Failed to fetch match:', error);
        });
    }
  }
};
</script>



<style scoped>

</style>

