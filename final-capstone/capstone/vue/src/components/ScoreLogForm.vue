<template>
  <div>
    <h2>Log Score</h2>
    <form @submit.prevent="logScore">
      <label for="username">Username:</label>
      <input type="text" v-model="username" required>
      <br>
      <label for="score">Score:</label>
      <input type="number" v-model="score" required>
      <br>
      <button type="submit">Log Score</button>
    </form>
  </div>
</template>

<script>
import ScoreService from '@/services/ScoreService';
import UserService from '@/services/UserService';
import TeeTimeService from '@/services/TeeTimeService';
import MatchPlayerService from '@/services/MatchPlayerService'

export default {
  data() {
    return {
      username: '',
      score: null
    };
  },
  methods: {
    logScore() {
      // Step 1: Get the userId based on the username
      UserService.getUserIdByUsername(this.username)
        .then(userId => {
          // Step 2: Get the matchId based on the userId
          MatchPlayerService.getMatchIdByPlayerId(userId)
            .then(matchId => {
              // Step 3: Get the teeTimeId based on the matchId
              TeeTimeService.getTeeTimeIdByMatchId(matchId)
                .then(teeTimeId => {
                  // Step 4: Get the leagueId based on the teeTimeId
                  TeeTimeService.getLeagueIdByTeeTimeId(teeTimeId)
                    .then(leagueId => {
                      // Step 5: Log the score in the league
                      const scoreData = {
                        playerId: userId,
                        matchId,
                        leagueId,
                        score: this.score
                      };
                      ScoreService.createScore(scoreData)
                        .then(() => {
                          // Score logged successfully
                          console.log('Score logged successfully');
                        })
                        .catch(error => {
                          console.error('Failed to log score', error);
                        });
                    })
                    .catch(error => {
                      console.error('Failed to get leagueId', error);
                    });
                })
                .catch(error => {
                  console.error('Failed to get teeTimeId', error);
                });
            })
            .catch(error => {
              console.error('Failed to get matchId', error);
            });
        })
        .catch(error => {
          console.error('Failed to get userId', error);
        });
    }
  }
};
</script>





<style scoped>

</style>

