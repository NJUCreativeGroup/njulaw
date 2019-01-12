<template>
  <div>
    <layout>
        <div style="padding-top: 100px">
          <search-bar></search-bar>
            <div style="margin-bottom: 20px;">
                <div v-if="docList.length<=0">
                    <Spin size="large" fix></Spin>
                </div>
                <div v-for="docItem in docList">
                  <div style="margin-bottom: 20px;margin-top: 10px; ">
                    <div style="display: inline-block;float: top;padding: 5px;background-color: white">
                      <a style="font-size: 18px; text-align: left"
                         :href=getRedirectUrl(docItem)>{{docItem.title}}</a>
                      <div style="font-size: 14px;max-width: 1000px;">
                        {{docItem.summary}}
                      </div>
                      <!--<div v-if="searchTuple.author" style="font-size: 10px;color: gray">-->
                        <!--{{searchTuple.author}}-->
                      <!--</div>-->
                    </div>
                  </div>
                </div>
            </div>
        </div>
    </layout>
  </div>
</template>

<script>
import { searchDoc } from '../api/api'
import SearchBar from './SearchBar'
export default {
  components:{
    SearchBar
  },
  data () {
    return {
      docList: [],
      kw: ''
    }
  },
  async mounted () {
    this.kw = this.$route.params.kw
    console.log('keyword  ' + this.kw)
    this.search()
  },
  methods: {
    async search () {
      this.docList = await searchDoc(this.kw)
      console.log(this.docList)
    },
    getRedirectUrl(document){
      return "/content/"+document.id;
    }
  }
}
</script>

<style scoped>
</style>
