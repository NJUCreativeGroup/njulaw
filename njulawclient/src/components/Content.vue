<template>
  <div style="padding-top: 100px">
    <search-bar></search-bar>

    <div style="margin-bottom: 20px;margin-top: 20px; ">
      <div style="display: inline-block;float: top;padding: 5px;background-color: white">
        <a style="font-size: 18px; text-align: left"
           :href= getRedirectUrl() >{{document.title}}</a>
        <div style="font-size: 14px;max-width: 1000px;">
          {{document.content}}
        </div>
      </div>
    </div>
  </div>

</template>

<script>
  import SearchBar from './SearchBar'
  import {getDocContent} from '../api/api'
  export default {
    name: 'Content',
    data(){
      return{
        id: 0,
        document: undefined
      }
    },
    async mounted(){
      this.id = this.$route.params.id
      console.log(this.id)
      this.getDoc()
    },
    methods:{
      async getDoc(){
        this.document = await getDocContent(this.id)
      },

      getRedirectUrl(){
        return "/content/"+this.id;
      }
    },
    components:{
      SearchBar
    },
  }
</script>

<style scoped>

</style>
