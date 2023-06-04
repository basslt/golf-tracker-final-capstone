<template>
    <div>
        <div class="message-card">
            <h2>{{message.content}}</h2>
        </div>
    </div>
</template>

<script>

export default {
    name: 'message-card',
    props: {
        message: {
            type: Object
        }
    }

    // data() {
    //     return {
    //         messages: []
    //     }
    // },
    // created() {
    //     this.messages = this.$store.state.messages;
    // }

}
</script>

<style scoped>

.message-card{
    border: solid;
}

</style>