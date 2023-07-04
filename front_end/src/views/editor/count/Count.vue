<template>
    <el-container>
        <el-container>
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>问卷数量</span>
                </div>
                <div class="text item">
                    {{'列表内容 ' + o }}
                </div>
            </el-card>
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>问卷数量</span>
                </div>
                <div class="text item">
                    {{'列表内容 ' + o }}
                </div>
            </el-card>
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>问卷数量</span>
                </div>
                <div class="text item">
                    {{'列表内容 ' + o }}
                </div>
            </el-card>
            <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>问卷数量</span>
            </div>
            <div class="text item">
                {{'列表内容 ' + o }}
            </div>
        </el-card>
        </el-container>
    </el-container>
</template>

<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {

    data() {
        return {

        }
    },
    mounted() {

    },
    computed: {

    },
    methods: {

    }
}
</script>

<style>
.text {
    font-size: 14px;
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
    border-radius: 20px;
    width: 480px;
}
</style>
