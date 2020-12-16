// 图书分类
$(function () {

    function findAll() {
        $.get("/booktype/findAll", response => {
            if (response.status == 20000) {
                var str = "";
                response.data.forEach(item => {
                    str += `<tr>
                                <td>${item.typeId}</td>
                                <td>${item.typeName}</td>
                                <td>${item.parentId == 0 ? "一级" : "二级"}</td>
                                <td>
                                    <button class="btn btn-sm  btn-info" data-toggle="modal"
                                            data-target="#modal-default" typeId="${item.typeId}">
                                        编辑
                                    </button>
                                    <button class="btn btn-sm  btn-warning" data-toggle="modal" data-target="#delModal" typeId="${item.typeId}">
                                        删除
                                    </button>
                                </td>
                            </tr>`;
                })
                $("tbody").html(str);
                setListener();
            }
        })
    }

    findAll();

    //初始化分类id
    var typeId = null;

    function setListener() {
        //删除按钮
        $(".btn-warning").click(function () {
            typeId = $(this).attr("typeId");
        });
        //确认删除
        $(".delSure").click(function () {
            deleteType(typeId);
        });
        //编辑按钮
        $(".btn-info").click(function () {
            typeId = $(this).attr("typeId");
            getTypeInfo(typeId);
        });
        //确认添加/编辑按钮
        $("#addOrEdit").click(function () {
            //新增
            if (document.getElementById("typeId").innerText == "") {
                addType();
                resetInput();
            //更新
            } else {
                updateType();
                resetInput();
            }
        });
        //点击取消按钮时重置输入框
        $(".btn-default").click(function () {
            resetInput();
        });
        //当display为none时 重置输入框
        // $("#modal-default").css("display").change(function () {
        //     if ($("#modal-default").css("display") == "none") {
        //         resetInput();
        //     }
        // });
    }


//删除方法
    function deleteType(typeId) {
        $.get(`/booktype/deleteBookType?typeId=${typeId}`, function (response) {
            if (response.status == 20000) {
                findAll();
            }
        })
    }

//新增分类
    function addType() {
        $.post("/booktype/addBookType", $("#addOrEditForm").serialize(), function (response) {
            if (response.status == 20000) {
                findAll();
            }
        });
    }

//获取当前分类信息
    function getTypeInfo(typeId) {
        $.get(`/booktype/findTypeById?typeId=${typeId}`, function (response) {
            if (response.status == 20000) {
                $("#typeName").get(0).value = response.data.typeName;
                $("#parentId").get(0).value = response.data.parentId;
                $("#typeId").get(0).value = response.data.typeId;
                $("#typeId").attr("value",response.data.typeId);
                typeId = response.data.typeId;
                document.getElementById("typeId").innerText = response.data.typeId;
            }
        })
    }

//更新分类信息
    function updateType() {
        $.post(`/booktype/updateType?typeId=${typeId}`, $("#addOrEditForm").serialize(), function (response) {
            if (response.status == 20000) {
                $("#typeName").get(0).value = response.data.typeName;
                $("#parentId").get(0).value = response.data.parentId;
                document.getElementById("typeId").innerText = null;
                findAll();
            }
        })
    }

//重置输入框
    function resetInput() {
        $("#addOrEditForm").get(0).reset();
        document.getElementById("typeId").innerText = null;
        $("#typeId").attr("value",null);


    }


})