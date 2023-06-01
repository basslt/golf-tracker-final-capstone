<template>
  <div class="main">
    <h2>Current Leaderboard</h2>
    <img src="../assets/Empty_Leaderboard.jpg" alt="">
    <ul>
      <li v-for="member in leaderboardMembers" :key="member.id">
        {{ member.name }} - {{ member.score }}
      </li>
    </ul>
  </div>
</template>

<script>
import LeagueMembershipService from '../services/LeagueMembership';
import UserService from '../services/UserService';
import ScoreService from '../services/ScoreService';

export default {
  props: {
    leagueId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      leaderboardMembers: []
    };
  },
  mounted() {
    this.fetchLeaderboardMembers();
  },
  methods: {
    fetchLeaderboardMembers() {
      LeagueMembershipService.getLeagueMembershipByLeagueId(this.leagueId)
        .then(memberships => {
          const memberIds = memberships.map(membership => membership.userId);
           UserService.getUsersByIds(memberIds)
            .then(users => {
              this.leaderboardMembers = users.map(user => ({
                id: user.id,
                name: user.name,
                score: 0 
              }));

              
              this.fetchScoresForMember();
            })
            .catch(error => {
              console.error('Failed to fetch user details:', error);
            });
        })
        .catch(error => {
          console.error('Failed to fetch leaderboard members:', error);
        });
    },
    fetchScoresForMember() {
     
      this.leaderboardMembers.forEach(member => {
        ScoreService.getScoresByPlayer(member.id)
          .then(scores => {
           
            const totalScore = scores.reduce((sum, score) => sum + score.value, 0);
            member.score = totalScore;
          })
          .catch(error => {
            console.error('Failed to fetch scores:', error);
          });
      });
    }
  }
};
</script>

<style>
div.main {
  display: flex;
  flex-direction: column;
  align-items: center;
}

ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

li {
  margin-bottom: 10px;
}
</style>
