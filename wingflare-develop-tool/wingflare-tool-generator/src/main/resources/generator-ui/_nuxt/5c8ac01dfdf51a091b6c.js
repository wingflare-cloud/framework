(window.webpackJsonp=window.webpackJsonp||[]).push([[3],{451:function(e,t,o){"use strict";o(72),o(101);var l={props:["content"]},r=o(46),component=Object(r.a)(l,(function(){var e=this._self._c;return e("el-tooltip",{attrs:{content:this.content}},[e("i",{staticClass:"el-icon-question",staticStyle:{cursor:"hand"}})])}),[],!1,null,null,null);t.a=component.exports},456:function(e,t,o){(function(o){var l,r,n;r=[],void 0===(n="function"==typeof(l=function(){"use strict";function t(a,b,e){var t=new XMLHttpRequest;t.open("GET",a),t.responseType="blob",t.onload=function(){g(t.response,b,e)},t.onerror=function(){console.error("could not download file")},t.send()}function l(a){var b=new XMLHttpRequest;b.open("HEAD",a,!1);try{b.send()}catch(a){}return 200<=b.status&&299>=b.status}function r(a){try{a.dispatchEvent(new MouseEvent("click"))}catch(e){var b=document.createEvent("MouseEvents");b.initMouseEvent("click",!0,!0,window,0,0,0,80,20,!1,!1,!1,!1,0,null),a.dispatchEvent(b)}}var n="object"==typeof window&&window.window===window?window:"object"==typeof self&&self.self===self?self:"object"==typeof o&&o.global===o?o:void 0,a=n.navigator&&/Macintosh/.test(navigator.userAgent)&&/AppleWebKit/.test(navigator.userAgent)&&!/Safari/.test(navigator.userAgent),g=n.saveAs||("object"!=typeof window||window!==n?function(){}:"download"in HTMLAnchorElement.prototype&&!a?function(b,g,e){var i=n.URL||n.webkitURL,o=document.createElement("a");g=g||b.name||"download",o.download=g,o.rel="noopener","string"==typeof b?(o.href=b,o.origin===location.origin?r(o):l(o.href)?t(b,g,e):r(o,o.target="_blank")):(o.href=i.createObjectURL(b),setTimeout((function(){i.revokeObjectURL(o.href)}),4e4),setTimeout((function(){r(o)}),0))}:"msSaveOrOpenBlob"in navigator?function(e,g,o){if(g=g||e.name||"download","string"!=typeof e)navigator.msSaveOrOpenBlob(function(a,b){return void 0===b?b={autoBom:!1}:"object"!=typeof b&&(console.warn("Deprecated: Expected third argument to be a object"),b={autoBom:!b}),b.autoBom&&/^\s*(?:text\/\S*|application\/xml|\S*\/\S*\+xml)\s*;.*charset\s*=\s*utf-8/i.test(a.type)?new Blob(["\ufeff",a],{type:a.type}):a}(e,o),g);else if(l(e))t(e,g,o);else{var i=document.createElement("a");i.href=e,i.target="_blank",setTimeout((function(){r(i)}))}}:function(b,e,o,g){if((g=g||open("","_blank"))&&(g.document.title=g.document.body.innerText="downloading..."),"string"==typeof b)return t(b,e,o);var l="application/octet-stream"===b.type,i=/constructor/i.test(n.HTMLElement)||n.safari,r=/CriOS\/[\d]+/.test(navigator.userAgent);if((r||l&&i||a)&&"undefined"!=typeof FileReader){var m=new FileReader;m.onloadend=function(){var a=m.result;a=r?a:a.replace(/^data:[^;]*;/,"data:attachment/file;"),g?g.location.href=a:location=a,g=null},m.readAsDataURL(b)}else{var f=n.URL||n.webkitURL,c=f.createObjectURL(b);g?g.location=c:location.href=c,g=null,setTimeout((function(){f.revokeObjectURL(c)}),4e4)}});n.saveAs=g.saveAs=g,e.exports=g})?l.apply(t,r):l)||(e.exports=n)}).call(this,o(27))},466:function(e,t,o){"use strict";o.r(t);o(236),o(58);var l=o(72),r=o.n(l),n=o(101),m=o.n(n),f=o(456),c=o.n(f),d=o(451),h={props:["userConfig"],components:{HelpTip:d.a},data:function(){return{superDoColumn:"",tableFillCol:"",tableFillType:"insert",form:{superDoClass:"",superDoColumns:[],tableFills:[],doSerialVersionUID:!1,doColumnConstant:!1,activeRecord:!1,doBuilderModel:!1,doLombokModel:!1,doBooleanColumnRemoveIsPrefix:!1,doTableFieldAnnotationEnable:!1,versionFieldName:"",logicDeleteFieldName:"",swagger2:!1},rules:{}}},watch:{userConfig:function(){m.a.assign(this.form,this.userConfig.doStrategy)}},mounted:function(){m.a.assign(this.form,this.userConfig.doStrategy)},methods:{addNewColumn:function(){this.form.superDoColumns||(this.form.superDoColumns=[]),this.superDoColumn?(-1===this.form.superDoColumns.indexOf(this.superDoColumn)&&this.form.superDoColumns.push(this.superDoColumn),this.superDoColumn=""):this.$message.warning("请输入字段名")},removeColumn:function(col){this.form.superDoColumns&&this.form.superDoColumns.splice(this.form.superDoColumns.indexOf(col),1)},addNewtableFill:function(){if(this.form.tableFills||(this.form.tableFills=[]),this.tableFillCol){var e=this.tableFillCol+":"+this.tableFillType;-1===this.form.tableFills.indexOf(e)&&this.form.tableFills.push(e),this.tableFillCol=""}else this.$message.warning("请输入字段名")},removeTableFill:function(col){this.form.tableFills&&this.form.tableFills.splice(this.form.tableFills.indexOf(col),1)},onSubmit:function(){var e=this;r.a.post("/api/output-file-info/save-do-strategy",this.form).then((function(t){e.$message.success("信息保存成功")}))}}},v=o(46),y=Object(v.a)(h,(function(){var e=this,t=e._self._c;return t("el-form",{ref:"editForm",attrs:{model:e.form,"label-width":"180px",size:"mini"}},[t("el-form-item",{attrs:{label:"Do的超类名称"}},[t("el-input",{model:{value:e.form.superDoClass,callback:function(t){e.$set(e.form,"superDoClass",t)},expression:"form.superDoClass"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"Do的公共字段"}},[t("el-input",{staticStyle:{width:"200px"},model:{value:e.superDoColumn,callback:function(t){e.superDoColumn=t},expression:"superDoColumn"}}),e._v(" "),t("el-button",{on:{click:e.addNewColumn}},[e._v("新增字段")]),e._v(" "),t("help-tip",{attrs:{content:"公共字段默认是从超类中继承的，即使表里面存在相关字段也不会生成到Do中（注意，要配置数据库原始字段名，不是超类中的属性名！！）"}}),e._v(" "),t("div",{staticStyle:{"margin-top":"5px"}},e._l(e.form.superDoColumns,(function(col){return t("el-tag",{key:col,staticStyle:{"margin-right":"5px"},attrs:{closable:""},on:{close:function(t){return e.removeColumn(col)}}},[e._v(e._s(col))])})),1)],1),e._v(" "),t("el-form-item",{attrs:{label:"需要自动填充的字段"}},[t("el-input",{staticStyle:{width:"300px"},model:{value:e.tableFillCol,callback:function(t){e.tableFillCol=t},expression:"tableFillCol"}},[t("el-select",{staticStyle:{width:"120px"},attrs:{slot:"prepend",placeholder:"请选择"},slot:"prepend",model:{value:e.tableFillType,callback:function(t){e.tableFillType=t},expression:"tableFillType"}},[t("el-option",{attrs:{label:"insert_update",value:"insert_update"}}),e._v(" "),t("el-option",{attrs:{label:"insert",value:"insert"}}),e._v(" "),t("el-option",{attrs:{label:"update",value:"update"}})],1)],1),e._v(" "),t("el-button",{on:{click:e.addNewtableFill}},[e._v("新增字段")]),e._v(" "),t("help-tip",{attrs:{content:"设置了自定填充的字段，将会在字段上自动添加对应的注解项，例如：@TableField(fill = FieldFill.INSERT_UPDATE)"}}),e._v(" "),t("div",{staticStyle:{"margin-top":"5px"}},e._l(e.form.tableFills,(function(col){return t("el-tag",{key:col,staticStyle:{"margin-right":"5px"},attrs:{closable:""},on:{close:function(t){return e.removeTableFill(col)}}},[e._v(e._s(col))])})),1)],1),e._v(" "),t("el-form-item",{attrs:{label:"标识乐观锁的字段名"}},[t("el-input",{model:{value:e.form.versionFieldName,callback:function(t){e.$set(e.form,"versionFieldName",t)},expression:"form.versionFieldName"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"标识逻辑删除的字段名"}},[t("el-input",{model:{value:e.form.logicDeleteFieldName,callback:function(t){e.$set(e.form,"logicDeleteFieldName",t)},expression:"form.logicDeleteFieldName"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"是否生成serialVersionUID",placeholder:""}},[t("el-switch",{model:{value:e.form.doSerialVersionUID,callback:function(t){e.$set(e.form,"doSerialVersionUID",t)},expression:"form.doSerialVersionUID"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"是否生成字段名常量",placeholder:""}},[t("el-switch",{model:{value:e.form.doColumnConstant,callback:function(t){e.$set(e.form,"doColumnConstant",t)},expression:"form.doColumnConstant"}}),e._v(" "),t("help-tip",{attrs:{content:"为每个字段生成一个静态常量，如：public static final String ID = 'test_id'"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"是否启用ActiveRecord模式",placeholder:""}},[t("el-switch",{model:{value:e.form.activeRecord,callback:function(t){e.$set(e.form,"activeRecord",t)},expression:"form.activeRecord"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"是否启用构建者模式",placeholder:""}},[t("el-switch",{model:{value:e.form.doBuilderModel,callback:function(t){e.$set(e.form,"doBuilderModel",t)},expression:"form.doBuilderModel"}})],1),e._v(" "),t("el-form-item",{attrs:{label:" 是否启用Lombok注解",placeholder:""}},[t("el-switch",{model:{value:e.form.doLombokModel,callback:function(t){e.$set(e.form,"doLombokModel",t)},expression:"form.doLombokModel"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"是否移除字段的is前缀",placeholder:""}},[t("el-switch",{model:{value:e.form.doBooleanColumnRemoveIsPrefix,callback:function(t){e.$set(e.form,"doBooleanColumnRemoveIsPrefix",t)},expression:"form.doBooleanColumnRemoveIsPrefix"}}),e._v(" "),t("help-tip",{attrs:{content:"比如 : 数据库字段名称 : 'is_xxx',类型为 : tinyint. 在映射实体的时候则会去掉is,在实体类中映射最终结果为 xxx"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"是否强制生成字段名注解",placeholder:""}},[t("el-switch",{model:{value:e.form.doTableFieldAnnotationEnable,callback:function(t){e.$set(e.form,"doTableFieldAnnotationEnable",t)},expression:"form.doTableFieldAnnotationEnable"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"是否生成swagger2注解",placeholder:""}},[t("el-switch",{model:{value:e.form.swagger2,callback:function(t){e.$set(e.form,"swagger2",t)},expression:"form.swagger2"}})],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("保存")])],1)],1)}),[],!1,null,null,null).exports,C={props:["userConfig"],components:{HelpTip:d.a},data:function(){return{form:{baseResultMap:!1,enableCache:!1},rules:{}}},watch:{userConfig:function(){m.a.assign(this.form,this.userConfig.mapperXmlStrategy)}},mounted:function(){m.a.assign(this.form,this.userConfig.mapperXmlStrategy)},methods:{onSubmit:function(){var e=this;r.a.post("/api/output-file-info/save-mapper-xml-strategy",this.form).then((function(t){e.$message.success("信息保存成功")}))}}},_=Object(v.a)(C,(function(){var e=this,t=e._self._c;return t("el-form",{ref:"editForm",attrs:{model:e.form,"label-width":"180px",size:"mini"}},[t("el-form-item",{attrs:{label:"是否生成baseResultMap",placeholder:""}},[t("el-switch",{model:{value:e.form.baseResultMap,callback:function(t){e.$set(e.form,"baseResultMap",t)},expression:"form.baseResultMap"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"是否在xml中添加二级缓存配置",placeholder:""}},[t("el-switch",{model:{value:e.form.enableCache,callback:function(t){e.$set(e.form,"enableCache",t)},expression:"form.enableCache"}})],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("保存")])],1)],1)}),[],!1,null,null,null).exports,w={props:["userConfig"],components:{HelpTip:d.a},data:function(){return{form:{superMapperClass:""},rules:{}}},watch:{userConfig:function(){m.a.assign(this.form,this.userConfig.mapperStrategy)}},mounted:function(){m.a.assign(this.form,this.userConfig.mapperStrategy)},methods:{onSubmit:function(){var e=this;r.a.post("/api/output-file-info/save-mapper-strategy",this.form).then((function(t){e.$message.success("信息保存成功")}))}}},x=Object(v.a)(w,(function(){var e=this,t=e._self._c;return t("el-form",{ref:"editForm",attrs:{model:e.form,"label-width":"180px",size:"mini"}},[t("el-form-item",{attrs:{label:"Mapper的超类名称"}},[t("el-input",{model:{value:e.form.superMapperClass,callback:function(t){e.$set(e.form,"superMapperClass",t)},expression:"form.superMapperClass"}})],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("保存")])],1)],1)}),[],!1,null,null,null).exports,S={props:["userConfig"],components:{HelpTip:d.a},data:function(){return{form:{superServiceClass:""},rules:{}}},watch:{userConfig:function(){m.a.assign(this.form,this.userConfig.serviceStrategy)}},mounted:function(){m.a.assign(this.form,this.userConfig.serviceStrategy)},methods:{onSubmit:function(){var e=this;r.a.post("/api/output-file-info/save-service-strategy",this.form).then((function(t){e.$message.success("信息保存成功")}))}}},k=Object(v.a)(S,(function(){var e=this,t=e._self._c;return t("el-form",{ref:"editForm",attrs:{model:e.form,"label-width":"180px",size:"mini"}},[t("el-form-item",{attrs:{label:"Service的超类名称"}},[t("el-input",{model:{value:e.form.superServiceClass,callback:function(t){e.$set(e.form,"superServiceClass",t)},expression:"form.superServiceClass"}})],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("保存")])],1)],1)}),[],!1,null,null,null).exports,F={props:["userConfig"],components:{HelpTip:d.a},data:function(){return{form:{superServiceImplClass:""},rules:{}}},watch:{userConfig:function(){m.a.assign(this.form,this.userConfig.serviceImplStrategy)}},mounted:function(){m.a.assign(this.form,this.userConfig.serviceImplStrategy)},methods:{onSubmit:function(){var e=this;r.a.post("/api/output-file-info/save-service-impl-strategy",this.form).then((function(t){e.$message.success("信息保存成功")}))}}},T=Object(v.a)(F,(function(){var e=this,t=e._self._c;return t("el-form",{ref:"editForm",attrs:{model:e.form,"label-width":"180px",size:"mini"}},[t("el-form-item",{attrs:{label:"ServiceImpl的超类名称"}},[t("el-input",{model:{value:e.form.superServiceImplClass,callback:function(t){e.$set(e.form,"superServiceImplClass",t)},expression:"form.superServiceImplClass"}})],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("保存")])],1)],1)}),[],!1,null,null,null).exports,L={props:["userConfig"],components:{HelpTip:d.a},data:function(){return{form:{superControllerClass:"",restControllerStyle:!1,controllerMappingHyphenStyle:!1},rules:{}}},watch:{userConfig:function(){m.a.assign(this.form,this.userConfig.controllerStrategy)}},mounted:function(){m.a.assign(this.form,this.userConfig.controllerStrategy)},methods:{onSubmit:function(){var e=this;r.a.post("/api/output-file-info/save-controller-strategy",this.form).then((function(t){e.$message.success("信息保存成功")}))}}},D={props:[],components:{DoStrategyForm:y,MapperStrategyForm:x,MapperXmlStrategyForm:_,ServiceStrategyForm:k,ServiceImplStrategyForm:T,ControllerStrategyForm:Object(v.a)(L,(function(){var e=this,t=e._self._c;return t("el-form",{ref:"editForm",attrs:{model:e.form,"label-width":"180px",size:"mini"}},[t("el-form-item",{attrs:{label:"Controller的超类名称"}},[t("el-input",{model:{value:e.form.superControllerClass,callback:function(t){e.$set(e.form,"superControllerClass",t)},expression:"form.superControllerClass"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"启用REST接口注解",placeholder:""}},[t("el-switch",{model:{value:e.form.restControllerStyle,callback:function(t){e.$set(e.form,"restControllerStyle",t)},expression:"form.restControllerStyle"}}),e._v(" "),t("help-tip",{attrs:{content:"用@RestController注解替换@Controller"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"Mapping Url格式转换",placeholder:""}},[t("el-switch",{model:{value:e.form.controllerMappingHyphenStyle,callback:function(t){e.$set(e.form,"controllerMappingHyphenStyle",t)},expression:"form.controllerMappingHyphenStyle"}}),e._v(" "),t("help-tip",{attrs:{content:"驼峰转连字符，如：/managerUserActionHistory -> /manager-user-action-history "}})],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("保存")])],1)],1)}),[],!1,null,null,null).exports,HelpTip:d.a},data:function(){return{activeName:"base",isUpdate:!1,uplaodFileList:[],uploadParams:{fileType:""},form:{fileType:"",pkgSuffix:"",packageName:"",outputLocationPkg:"",outputLocationPrefix:"",outputLocation:"",templateName:"",templatePath:"",builtIn:!1},rules:{fileType:[{required:!0,message:"请输入文件类型",trigger:"change"}],outputLocationPkg:[{required:!0,message:"请输入保存文件的包名",trigger:"change"}],templateName:[{required:!0,message:"请上传文件模板",trigger:"change"}]},userConfig:{},showEditForm:!1,searchKey:"",tables:[],choosedTables:[]}},mounted:function(){this.getFileInfos()},methods:{editFileInfo:function(e){if(this.form=Object.assign(this.form,e),this.isUpdate=!0,this.uploadParams.fileType=this.form.fileType,this.form.outputLocation)if(-1!==this.form.outputLocation.indexOf(":")){var t=this.form.outputLocation.split(":");this.form.outputLocationPrefix=t[0],this.form.outputLocationPkg=t[1]}else this.form.outputLocationPkg=this.form.outputLocation;this.form.outputLocationPrefix||("Mapper.xml"===this.form.fileType?this.form.outputLocationPrefix="resources":this.form.outputLocationPrefix="java"),this.showEditForm=!0},getFileInfos:function(){var e=this;r.a.get("/api/output-file-info/user-config").then((function(t){e.userConfig=t}))},clearForm:function(){this.form.fileType="",this.form.pkgSuffix="",this.form.outputLocationPrefix="",this.form.outputLocationPkg="",this.form.outputLocation="",this.form.templateName="",this.form.templatePath="",this.form.builtIn=!1},addNew:function(){this.showEditForm=!0,this.isUpdate=!1,this.clearForm()},beforeTemplateUpload:function(e){var t=e.name.split("."),o=t[t.length-1],l=e.size/1024/1024<2;return"vm"!=o?(this.$message.error("请选择正确的模板文件格式"),!1):!!l||(this.$message.error("模板文件不能超过 2MB!"),!1)},onSubmit:function(){var e=this;this.$refs.editForm.validate((function(t){if(!t)return!1;e.form.outputLocation=e.form.outputLocationPrefix+":"+e.form.outputLocationPkg,r.a.post("/api/output-file-info/save",e.form).then((function(t){e.$message.success("信息保存成功"),e.clearForm(),e.activeName="base",e.showEditForm=!1,e.getFileInfos()}))}))},deleteFileInfo:function(e){var t=this;this.$confirm("确认删除?","操作提示",{type:"warning"}).then((function(){r.a.post("/api/output-file-info/delete",e).then((function(e){t.$message({message:"输出文件已删除",type:"success"}),t.getFileInfos()}))}))},download:function(e,t){r.a.get("/api/template/download",{params:{fileType:e},responseType:"blob"}).then((function(e){c.a.saveAs(e,t)}))},onUploadSuccess:function(e,t,o){200===e.code?(this.$message.success("模板文件上传成功"),this.form.templateName=e.data.templateName,this.form.templatePath=e.data.templatePath,this.$refs.upload.clearFiles()):this.$message.error("模板文件上传失败")}}},$=Object(v.a)(D,(function(){var e=this,t=e._self._c;return t("div",[t("div",{staticClass:"content-header"}),e._v(" "),t("div",{staticClass:"content"},[t("div",{staticClass:"container"},[t("div",{staticClass:"pull-right",staticStyle:{"margin-bottom":"10px"}},[t("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.addNew()}}},[e._v("新增")])],1),e._v(" "),t("el-table",{staticStyle:{width:"100%"},attrs:{data:e.userConfig.outputFiles,border:""}},[t("el-table-column",{attrs:{prop:"fileType",label:"输出文件类型"}}),e._v(" "),t("el-table-column",{attrs:{prop:"pkgSuffix",label:"包名后缀"}}),e._v(" "),t("el-table-column",{attrs:{width:"500",prop:"outputLocation",label:"输出路径"}}),e._v(" "),t("el-table-column",{attrs:{label:"文件模板"},scopedSlots:e._u([{key:"default",fn:function(o){return[t("a",{attrs:{href:"javascript:;"},on:{click:function(t){return e.download(o.row.fileType,o.row.templateName)}}},[t("i",{staticClass:"fa fa-paperclip"}),e._v("\n              "+e._s(o.row.templateName)+"\n            ")])]}}])}),e._v(" "),t("el-table-column",{attrs:{label:"是否内置",width:"80"},scopedSlots:e._u([{key:"default",fn:function(o){return[o.row.builtIn?t("span",{staticStyle:{color:"red"}},[e._v("是")]):t("span",[e._v("否")])]}}])}),e._v(" "),t("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(o){return[t("el-button",{attrs:{size:"mini",type:"info",icon:"el-icon-edit",circle:""},on:{click:function(t){return e.editFileInfo(o.row)}}}),e._v(" "),o.row.builtIn?e._e():t("el-button",{attrs:{size:"mini",circle:"",type:"warning",icon:"el-icon-delete"},on:{click:function(t){return e.deleteFileInfo(o.row)}}})]}}])})],1)],1),e._v(" "),t("el-dialog",{attrs:{visible:e.showEditForm,width:"70%",top:"5vh"},on:{"update:visible":function(t){e.showEditForm=t}}},[t("el-tabs",{model:{value:e.activeName,callback:function(t){e.activeName=t},expression:"activeName"}},[t("el-tab-pane",{attrs:{label:"基本配置",name:"base"}},[t("el-form",{ref:"editForm",attrs:{rules:e.rules,model:e.form,"label-width":"120px"}},[t("el-form-item",{attrs:{label:"类型标识",prop:"fileType",placeholder:"用于标识该类生成文件，如：服务接口,Controller等"}},[t("el-input",{attrs:{readonly:e.isUpdate},model:{value:e.form.fileType,callback:function(t){e.$set(e.form,"fileType",t)},expression:"form.fileType"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"包后缀",prop:"pkgSuffix",placeholder:"包后缀，分层结构的项目中非常有用"}},[t("el-input",{model:{value:e.form.pkgSuffix,callback:function(t){e.$set(e.form,"pkgSuffix",t)},expression:"form.pkgSuffix"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"包名",prop:"outputLocationPkg",placeholder:"例如：org.example.do"}},[t("el-input",{attrs:{placeholder:"org.example.do"},model:{value:e.form.outputLocationPkg,callback:function(t){e.$set(e.form,"outputLocationPkg",t)},expression:"form.outputLocationPkg"}},[t("el-select",{staticStyle:{width:"110px"},attrs:{slot:"prepend",placeholder:"请选择源码目录"},slot:"prepend",model:{value:e.form.outputLocationPrefix,callback:function(t){e.$set(e.form,"outputLocationPrefix",t)},expression:"form.outputLocationPrefix"}},[t("el-option",{attrs:{label:"java",value:"java"}}),e._v(" "),t("el-option",{attrs:{label:"resources",value:"resources"}})],1)],1),e._v(" "),t("help-tip",{attrs:{content:"生成的文件所输出的位置，选择不同的源码目录，会影响输出位置的根目录。例如选择resources，将会以resources作为包的根目录:example.mapper将保存到/resources/example/mapper目录中"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"文件模板",prop:"templateName"}},[t("a",{attrs:{href:"javascript:;"},on:{click:function(t){return e.download(e.form.fileType,e.form.templateName)}}},[e._v(e._s(e.form.templateName))]),e._v(" "),t("el-upload",{ref:"upload",attrs:{"show-file-list":!1,limit:1,data:e.uploadParams,"file-list":e.uplaodFileList,action:"/api/template/upload","on-success":e.onUploadSuccess,"before-upload":e.beforeTemplateUpload}},[t("el-button",{attrs:{slot:"trigger",size:"small",type:"primary"},slot:"trigger"},[e._v("选取文件")]),e._v(" "),t("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v("仅支持.btl文件")])],1)],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("保存")])],1)],1)],1),e._v(" "),e.form.builtIn?t("el-tab-pane",{attrs:{label:"策略配置",name:"strategy"}},["Do"==e.form.fileType?t("do-strategy-form",{attrs:{"user-config":e.userConfig}}):e._e(),e._v(" "),"Mapper.xml"==e.form.fileType?t("mapper-xml-strategy-form",{attrs:{"user-config":e.userConfig}}):e._e(),e._v(" "),"Mapper.java"==e.form.fileType?t("mapper-strategy-form",{attrs:{"user-config":e.userConfig}}):e._e(),e._v(" "),"Service"==e.form.fileType?t("service-strategy-form",{attrs:{"user-config":e.userConfig}}):e._e(),e._v(" "),"ServiceImpl"==e.form.fileType?t("service-impl-strategy-form",{attrs:{"user-config":e.userConfig}}):e._e(),e._v(" "),"Controller"==e.form.fileType?t("controller-strategy-form",{attrs:{"user-config":e.userConfig}}):e._e()],1):e._e()],1)],1)],1)])}),[],!1,null,null,null);t.default=$.exports}}]);