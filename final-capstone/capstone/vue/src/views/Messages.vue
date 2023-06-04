<template>
  <div>
    <header>
      <main-navigation />
    </header>
    <body class="main">
        <div class="content">
            <message-list />
        </div>
    </body>
  </div>
</template>

<script>
import MessageList from '../components/MessageList.vue'
import MainNavigation from '../components/MainNavigation.vue'

export default {
    components: {
        MessageList,
        MainNavigation
    }

}
</script>

<style scoped>

.main {
    display: flex;
    flex-direction: column;
    height: 100vh;
    width: 100vw;
    justify-content: center;
    align-items: center;
}

.content {
    
}

</style>