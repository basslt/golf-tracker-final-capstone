<template>
  <div class="container">
     <h1>Leaderboard</h1>
    <div class="table-container">
     
      <table class="leaderboard">
        
          <thead>
              <tr>
                <th>Username</th>
                <th>Scores</th>
                <th>Last Round</th>
              </tr>
          </thead>
          <tbody class="table-data">
              <tr v-for="userScore in memberNameScores" v-bind:key="userScore.id">
                  <td>{{ userScore.username }}</td>
                  <td class="average">{{userScore.totalScore}}</td>
                  <td class="last-round">{{userScore.latestScore}}</td>
              </tr>
              <tr v-if="memberNameScores.length === 0">
                  <td colspan="3">Play Rounds To See Leaderboard!</td>
              </tr>
          </tbody>
      </table>
  </div> 
  </div>
 
</template>

<script>
import leaderboardService from '../services/Leaderboard';
import userService from '../services/UserService';
import scoreService from '../services/ScoreService'

export default {
  props: {
    leagueId: {
      type: Number,
      required: true
    },
    // memberNameScores: {
    //   type: Array,
    //   required: true
    // }
  },
  data() {
    return {
      leagueMembers: [],
      memberScores: [],
      memberNameScores: [],
      latestMemberScore: []
    };
  },
  methods: {
      getLeagueMembers() {
          userService.findUsersInLeague(this.leagueId).then( (response) => {
            console.log(response.data)
              this.leagueMembers = response.data;
              this.getLatestScore(); 
          })
          .catch(error => {
              console.log(error);
          });
      },
      getOrderedLeaderboard() {
          leaderboardService.getOrderedLeaderboard(this.leagueId).then( (response) => {
            console.log(response.data);
            this.memberScores = response.data;
          }).catch(error => {
             console.log(error);
          });
      },

      combineUsersScores() {
        if (this.leagueMembers.length > 0 && this.memberScores.length > 0) {
        this.memberNameScores = this.memberScores.map( (score) => {
          const member = this.leagueMembers.find(member => member.id === score.userId);
          return {
            username: member.username,
            totalScore: score.totalScore,
            latestScore: this.latestMemberScore.find( 
              latestScore => latestScore.playerId === score.userId
            ).score
          };
        });
        console.log(this.memberNameScores);
        }
      },
      getLatestScore() {
        const requests = this.leagueMembers.map(member => {
          return scoreService.getLatestPlayerScore(this.leagueId, member.id)
          .then(response => response.data)
          .catch(error => {
            console.log(error);
            return null;
          });
        });

        Promise.all(requests).then(scores => {
          this.latestMemberScore = scores.filter(score => score !== null);
          this.combineUsersScores();
        })
        .catch(error => {
          console.log(error);
        })
      }
  },
  created() {
      this.getLeagueMembers();
      this.getOrderedLeaderboard();       
  },
}

</script>

<style scoped>
.container {
  background:#059262;
  padding: 10px;
  border-radius: 5px;
  
}



.table-container {
   display: flex;
    width: 100%;
    height: 400px;
    justify-content: flex-start;
    align-items: flex-start;

    
}

h1{
  display: flex;
  justify-content: center;
  color: white;
  margin-bottom: 10px;
  font-size: 40px;
}


table {
    width: 700px;
    border-collapse: collapse;
    text-align: center;
    color: white;
}

th {
    padding: 10px;
    border-bottom: 3px solid white;
  
    text-align: center;
}


td {
    padding: 10px;
    border-bottom: 3px solid white;
  
    vertical-align: center;
}

.average {
    border-left: 3px solid white;
    width: 25%;
}

.last-round {
    border-left: 3px solid white ;
    width: 25%;
}
</style>
