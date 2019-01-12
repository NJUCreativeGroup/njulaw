<template>

    <div style="height: 100vh;">
      <!--<div>-->
      <div class="banner banner-1"></div>
      <!--</div>-->
      <div class="search">
        <h1 style="color: orange;font-size:45px;">
          NJU法律智能
        </h1>
        <h2 style="color: orange;font-size:24px;">NJU LAW Intelligence</h2>
        <h3 style="color: white;font-size:24px;padding: 30px">
          —— 打造南大法律智能平台 ——
        </h3>
        <search-bar></search-bar>
      </div>

    </div>


</template>

<script>
import SearchBar from '@/components/SearchBar.vue'
export default {
  components: {
    SearchBar
  }
}
</script>

<style scoped>
  .banner-1 {
    background: url(../assets/images/images.jpeg) fixed;
    background-size: cover;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    -ms-background-size: cover;
    min-height: 100%;
  }
  .search {
    text-align: center;
    position: absolute;
    top: 354px;
    width: 50%;
    margin-left: 25%;
  }

  @media screen and (max-device-width: 500px) {
    .search {
      width: 70%;
      margin-left: 15%;
    }
  }
</style>
