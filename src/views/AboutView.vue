<template>
<div>
  <div>
  <el-input style="width:250px" v-model="params.name" placeholder="请输入查找信息"></el-input>
  <el-button style="margin-left:25px;" type="warning" @click="find()">查找</el-button>
  <el-button style="margin-left:25px;" type="warning" @click="reset()">清空搜索栏</el-button>
  <el-button style="margin-left:25px;" type="success" @click="add()">添加新的联系人</el-button>
  <el-button style="margin-left:25px;" type="success" @click="find1()">查看收藏联系人</el-button>
  <el-button type="success" style="margin-left: 10px" @click="exp()">导出表格</el-button>
  <el-upload
    class="upload-demo"
    drag
    action=""
    :show-file-list="false"
    :on-change="handleFileUpload"
    :before-upload="beforeFileUpload">
    <i class="el-icon-upload"></i>
    <div class="el-upload__text">拖拽文件到这里，或<em>点击上传</em></div>
    <div v-if="errorMessage" class="error">{{ errorMessage }}</div>
  </el-upload>

     <el-table
           :data="tableData"
           style="width: 100%">
           <el-table-column
             prop="name"
             label="姓名"
             width="200">
           </el-table-column>
           <el-table-column style="margin-left:50px;"
             prop="phone"
             label="电话"
             width="200">
           </el-table-column>
           <el-table-column
             prop="qq"
             label="QQ号"
             width="200">
           </el-table-column>
           <el-table-column
             prop="vx"
             label="微信号"
             width="200">
           </el-table-column>
           <el-table-column
             prop="youxiang"
             label="邮箱地址"
             width="200">
           </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                 <el-button type="primary" @click="edit(scope.row)">编辑</el-button>
                 <el-popconfirm title="确定删除吗？" @confirm="del(scope.row.id)">
                   <el-button slot="reference" type="danger" style="margin-left: 5px">删除</el-button>
                 </el-popconfirm>
                 <el-button type="primary" plain style="margin-left: 5px" @click="checkcollect(scope.row)">收藏</el-button>
                 <el-button slot="reference" plain type="danger" style="margin-left: 5px" @click="checkcollect1(scope.row)">取消收藏</el-button>

              </template>
               </el-table-column>
         </el-table>
  </div>

<div style="margin-top: 10px">
  <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="params.pageNum"
      :page-sizes="[5, 10, 15, 20]"
      :page-size="params.pageSize"
      layout="total, sizes, prev, pager, next"
      :total="total">
  </el-pagination>
</div>
<div>
  <el-dialog title="请填写信息" :visible.sync="dialogFormVisible" width="30%">
    <el-form :model="form">
      <el-form-item label="姓名" label-width="15%">
        <el-input v-model="form.name" autocomplete="off" style="width: 90%"></el-input>
      </el-form-item>
      <el-form-item label="电话" label-width="15%">
        <el-input v-model="form.phone" autocomplete="off" style="width: 90%"></el-input>
      </el-form-item>
      <el-form-item label="QQ号" label-width="15%">
        <el-input v-model="form.qq" autocomplete="off" style="width: 90%"></el-input>
      </el-form-item>
      <el-form-item label="微信号" label-width="15%">
        <el-input v-model="form.vx" autocomplete="off" style="width: 90%"></el-input>
      </el-form-item>
      <el-form-item label="邮箱地址" label-width="15%">
        <el-input v-model="form.youxiang" autocomplete="off" style="width: 90%"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="submit()">确 定</el-button>
    </div>
  </el-dialog>
</div>

  </div>
</template>

 <script>
 import * as XLSX from 'xlsx';
 import request from "@/utils/request";
    export default {
       name: "AboutView",
        data() {
          return {
            params:{
            name: '',
            isshoucang:1,
            pageNum: 1,
            pageSize: 5,
            },
            tableData: [],
            total: 0,
            dialogFormVisible:false,
            form:{}
          }
        },
        created() {
          this.find();
        },
        methods: {

          find(){
            request.get("/user/search",{
              params: this.params
            }).then(res => {
            if(res.code ==='0'){
              this.tableData = res.data.list;
              this.total = res.data.total;

            }else{

            }
          })

        },
        find1() {
        this.params = {
                pageNum: 1,
                pageSize: 5,
                isshoucang:1,
                name:'',
                }
        request.get("/user/isshoucang",{
            params: this.params
            }).then(res => {
            if(res.code ==='0'){
              this.tableData = res.data.list;
              this.total = res.data.total;

                    }else{

                    }
                  })
        },
        add(){
        this.form = {};
        this.dialogFormVisible = true;
        },
        submit(){
        request.post("/user", this.form).then(res => {
            if (res.code === '0') {
              this.$message({
                message: '操作成功',
                type: 'success'
              });
              this.dialogFormVisible = false;
              this.find();
            } else {
              this.$message({
                message: res.msg,
                type: 'success'
              });
            }
          })
        },
        edit(obj) {
        this.dialogFormVisible = true;
        this.form = obj;
        this.find();
        },
        del(id){
        request.delete("/user/" + id).then(res => {
            if (res.code === '0') {
              this.$message({
                message: '删除成功',
                type: 'success'
              });
              this.find ();
            } else {
              this.$message({
                message: res.msg,
                type: 'success'
              });
            }
          })

        },

        reset(){
        this.params = {
        pageNum: 1,
        pageSize: 5,
        name:'',
        }
        this.find();
        },
        handleSizeChange(pageSize){
        this.params.pageSize = pageSize;
        this.find();
        },
        handleCurrentChange(pageNum){
        this.params.pageNum = pageNum;
        this.find();
        },
        checkcollect(obj){
        this.form = obj;
        this.form.isshoucang = 1;
        this.dialogFormVisible = true;
        },
        checkcollect1(obj){
        this.form = obj;
        this.form.isshoucang = 0;
        this.dialogFormVisible = true;
        },
        exp() {
            // 准备要导出的数据
            const data = this.tableData.map(row => ({
              '姓名': row.name,
              '电话': row.phone,
              'QQ号': row.qq,
              '微信号': row.vx,
              '邮箱地址': row.youxiang
            }));

            // 创建一个工作簿并添加数据
            const ws = XLSX.utils.aoa_to_sheet(data);
            const wb = XLSX.utils.book_new();
            XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');

            // 生成Excel文件并触发下载
            XLSX.writeFile(wb, '联系人列表.xlsx');
          },
          beforeFileUpload(file) {
              // 在这里可以添加文件类型校验等逻辑
              const isExcel = file.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' || file.type === 'application/vnd.ms-excel';
              if (!isExcel) {
                this.errorMessage = '请上传Excel文件';
                return false;
              }
              this.errorMessage = '';
              return true;
            },

            handleFileUpload(file) {
              const reader = new FileReader();
              reader.onload = (e) => {
                const data = new Uint8Array(e.target.result);
                const workbook = XLSX.read(data, { type: 'array' });

                // 假设我们只读取第一个工作表
                const firstSheetName = workbook.SheetNames[0];
                const worksheet = workbook.Sheets[firstSheetName];

                // 将工作表转换为JSON
                const jsonData = XLSX.utils.sheet_to_json(worksheet, { header: 1 });

                // 在这里处理jsonData，例如更新tableData或发送到服务器
                console.log(jsonData);

                // 如果您想将导入的数据显示到表格中，您需要适当地处理jsonData并将其设置为tableData
                // 例如：this.tableData = jsonData.slice(1); // 假设第一行是标题行，所以跳过它

                // 注意：这里您可能需要根据您的实际数据结构进行适配
              };
              reader.readAsArrayBuffer(file.raw);
            }

        }
    }
  </script>
