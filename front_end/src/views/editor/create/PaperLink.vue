<template>
  <div class="paperlink">
    <div class="paperlink-header">
      <span style="font-size: 30px;font-weight: bold">问卷发放</span>
    </div>
    <div class="paperlink-URL">
      <span style="display: block; margin-top: 20px;font-size: 20px">
        问卷链接 :
          <a :href="paperURL">{{ paperURL }}</a>
      </span>

    </div>
      <div style="text-align: left; padding-left: 30px;margin-top: 20px">
          <el-button
                  class="copyBtn"
                  type="primary"
                  size=""
                  style="margin-top: 10px"
                  @click="copyURL()"
                  icon="el-icon-link"
                  :data-clipboard-text="paperURL"
          >
              复制链接
          </el-button>
          <el-button
                  type="primary"
                  size=""
                  @click="browsePaper(paperURL)"
                  icon="el-icon-view"
          >
              预览问卷
          </el-button>
      </div>

  </div>
</template>

<script>
import Clipboard from 'clipboard'
export default {
  name: 'PaperLink',
  data() {
    return {
      paperId: -1,
      paperURL: `http://localhost:8080/customer/${this.$route.params.paperId}`,
      show: true
    }
  },
  mounted() {
    this.paperId = this.$route.params.paperId
    this._cp = new Clipboard('.copyBtn')
  },
  destroyed() {
    this._cp.destroy()
  },
  methods: {
    copyURL() {
      this.$message({
        message: '复制成功!',
        type: 'success'
      })
    },
    browsePaper(paperURL) {
      console.log(paperURL)
      window.open(paperURL)
    }
  }
}
</script>

<style>
.paperlink {
  height: 100%;
}

.paperlink-header {
  height: 70px;
  line-height: 70px;
  text-align: left;
  padding-left: 30px;
  border-bottom: 1px groove;
}

.paperlink-URL {
  padding-left: 30px;
  text-align: left;
}
</style>
