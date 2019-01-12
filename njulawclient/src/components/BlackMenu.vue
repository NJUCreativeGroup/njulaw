<template>
    <Header :style="{position: 'fixed', width: '100%',zIndex:999}">
        <Menu mode="horizontal" theme="dark" active-name="0">
            <router-link :to="{path: '/'}">
                <MenuItem name="0">
                    <!--<img class="layout-logo" src="../assets/images/law.jpeg" style="height: 100%"/>-->
                    <div class="layout-text">
                        <h3 style="height: 30px;">
                            NJU法律智能
                        </h3>
                        <h6 style="height: 15px;margin-top:-7px">
                            NJU LAW Intelligence
                        </h6>
                    </div>
                </MenuItem>
            </router-link>
        </Menu>
    </Header>
</template>

<script>

  export default {
    data() {
      return {
      }
    },
    mounted() {
    }
  }
</script>

<style scoped>
    .layout-logo {
        width: auto;
        height: 30px;
        border-radius: 3px;
        float: left;
        position: relative;
        top: 13px;
        left: 20px;
        margin-right: 25px;
    }

    .layout-nav {
        margin: 0 20px 0 auto;
        float: right;
    }

    .layout-text {
        display: table;
        color: white;
        margin-top: -10px;
        padding-left: 10px;
    }
</style>
