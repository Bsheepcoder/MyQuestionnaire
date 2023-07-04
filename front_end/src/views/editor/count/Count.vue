<template>
    <el-container style="display:block">
        <div style="display: flex;width: 100%">
            <el-card class="box-card"  style="background-color: #23d96e" shadow="always">
                <div slot="header" class="clearfix" >
                    <span>问卷总数</span>
                </div>
                <div class="text item">
                    {{allPapers.length}}
                </div>
            </el-card>
            <el-card class="box-card" style="background-color: dodgerblue">
                <div slot="header" class="clearfix" >
                    <span>已发放</span>
                </div>
                <div class="text item">
                    {{startPapers.length}}
                </div>
            </el-card>
            <el-card class="box-card"  style="background-color: #ff3366">
                <div slot="header" class="clearfix">
                    <span>编辑中</span>
                </div>
                <div class="text item">
                    {{initPapers.length}}
                </div>
            </el-card>
            <el-card class="box-card"  style="background-color: orange">
                <div slot="header" class="clearfix">
                    <span>已结束</span>
                </div>
                <div class="text item">
                    {{endPapers.length}}
                </div>
            </el-card>
        </div>
        <el-divider></el-divider>
        <div style="text-align: left;margin-top: 40px">
          <span style="font-size: 30px"><i class="el-icon-loading" style="font-size: 30px;color: dodgerblue"></i> 正在进行的问卷 </span>
        </div>

        <div style="text-align: center;border-radius: 10px;box-shadow: 0px 2px 4px 4px #efefef;margin-top: 40px">
            <el-table
                :data="startPapers"
                stripe
                style="width: 100%;height:100%;font-size: 18px;text-align: center;border-radius: 10px;">
                <el-table-column
                    prop="title"
                    label="标题"
                    width="330" >
                </el-table-column>
                <el-table-column
                    prop="startTime"
                    label="开始时间"
                    width="280">
                </el-table-column>
                <el-table-column
                    prop="endTime"
                    label="结束时间"
                    width="280">
                </el-table-column>
                <el-table-column
                    prop="description"
                    label="描述">
                </el-table-column>
            </el-table>
        </div>
    </el-container>


</template>

<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {

    name: 'Count',
    data() {
        return {
        }
    },
    mounted() {
        this.getAllPapers()
        this.getStartPapers()
        this.getEditPapers()
        this.getEndPapers()
    },
    computed: {
        ...mapGetters(['allPapers','startPapers','initPapers','endPapers'])
    },
    methods: {
        ...mapActions(['getAllPapers', 'deletePaper', 'editOldPaper','getStartPapers','getEditPapers','getEndPapers']),
        ...mapMutations(['set_createPaperVisible']),
    }


}
</script>

<style>
.text {
    align-items: center;
    margin-top: 10px;
    font-size:50px;
}

.item {
    margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
    display: table;
    content: "";
}
.clearfix:after {
    clear: both
}

.box-card {
    font-weight: normal;
    color: #ffffff;
    box-shadow: gray;
    border-radius: 20px;
    width: 100%;
    margin: 20px;
    height: 200px;
    font-size: 30px;
}
</style>
