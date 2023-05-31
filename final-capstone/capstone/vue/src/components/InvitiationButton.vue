<template>
  <div>
    <button @click="inviteToLeague">Invite to League</button>
  </div>
</template>

<script>
export default {
  props: {
    leagueId: {
      type: String,
      required: true
    }
  },
  methods: {
    inviteToLeague() {
      // Invitation logic here
      console.log(`Inviting to league with ID: ${this.leagueId}`);
      // Perform actions like generating invitation link or opening a modal
    }
  }
}
</script>