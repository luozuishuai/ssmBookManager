// 图书分类
$(function () {

    var currentPage = 1;
    var pageSize = 10;
    var bookIds = [];

    /**
     * 初始化分页插件
     */
    function initPage(total) {
        $("#pagination").pagination(total,
            {
                callback: PageCallback,
                prev_text: "<< 上一页",
                next_text: "下一页 >>",
                items_per_page: pageSize,
                num_edge_entries: 2,
                num_display_entries: 5,
                current_page: currentPage - 1,
                load_first_page: false
            }
        )
    }

    /**
     * 分页插件回调函数
     * @param page
     * @constructor
     */
    function PageCallback(page) {
        currentPage = page + 1;
        searchPage();
    }

    /**
     * 分页条件查询
     */
    function searchPage() {
        $.post(`/book/searchPage?currentPage=${currentPage}&pageSize=${pageSize}`, $("#searchForm").serialize(), function (response) {
            if (response.status == 20000) {
                var str = "";
                response.data.list.forEach(item => {
                    str += `<tr bookId="${item.bookId}" class="bookInfo">
                                <td><input type="checkbox" class="bookCheck"></td>
                                <td>${item.bookId}</td>
                                <td>${item.bookName}</td>
                                <td>${item.authorName}</td>
                                <td>${item.firstTypeName}</td>
                                <td>${item.secondTypeName}</td>
                                <td><img src="${item.imgUrl}" style="width: 40px"></td>
                                <td>
                                    <button class="btn btn-sm  btn-info editBtn" data-toggle="modal"
                                            data-target="#modal-default">
                                        编辑
                                    </button>
                                    <button class="btn btn-sm  btn-warning" data-toggle="modal" data-target="#delModal">
                                        删除
                                    </button>
                                </td>
                            </tr>`;
                })
                $("tbody").html(str);
                //拿到总条数
                initPage(response.data.total);
                //开启按钮监听
                setButtonClickListener();

                //重置全选框,清空bookIds数组
                resetCheckBox();

                //判断当前页是否有内容
                if (response.data.list.length == 0 && currentPage != 1) {
                    currentPage--;
                    searchPage();
                }
            }

        });
    }

    searchPage();

    // function findAll() {
    //     $.get(`/book/findByPage?currentPage=${currentPage}&pageSize=${pageSize}`, response => {
    //         if (response.status == 20000) {
    //             var str = "";
    //             response.data.list.forEach(item => {
    //                 str += `<tr>
    //                             <td>${item.bookId}</td>
    //                             <td>${item.bookName}</td>
    //                             <td>${item.authorName}</td>
    //                             <td>${item.firstTypeName}</td>
    //                             <td>${item.secondTypeName}</td>
    //                             <td><img src="${item.imgUrl}" style="width: 40px"></td>
    //                             <td>
    //                                 <button class="btn btn-sm  btn-info" data-toggle="modal"
    //                                         data-target="#modal-default">
    //                                     编辑
    //                                 </button>
    //                                 <button class="btn btn-sm  btn-warning" data-toggle="modal" data-target="#delModal">
    //                                     删除
    //                                 </button>
    //                             </td>
    //                         </tr>`;
    //             })
    //             $("tbody").html(str);
    //             //拿到总条数
    //             initPage(response.data.total);
    //         }
    //     })
    // }
    //
    // findAll();


    /**
     * 通过parentId查询分类内容
     */
    function findBookTypeByParentId(id) {
        $.get(`/booktype/findByParentId?parentId=${id}`, response => {
            if (response.status == 20000) {
                if (id == 0) {
                    var str = `<option value="-1">一级分类</option>`;
                    response.data.forEach(item => {
                        str += `<option value=${item.typeId}>${item.typeName}</option>`;
                    })
                    $("#firstType2").html(str);
                    $("#firstType").html(str);
                } else {
                    var str = `<option value="-1">二级分类</option>`;
                    response.data.forEach(item => {
                        str += `<option value=${item.typeId}>${item.typeName}</option>`;
                    })
                    $("#secondType2").html(str);
                    $("#secondType").html(str);
                }
            }

        })
    }

    findBookTypeByParentId(0);

    //监听一级分类的变化事件
    function setSelectChangeListener() {
        $("#firstType").change(function () {
            findBookTypeByParentId($(this).val());
        });
        $("#firstType2").change(function () {
            findBookTypeByParentId($(this).val());
        });
    }

    setSelectChangeListener();

    /**
     * 给搜索按钮设置点击事件
     */
    $("#searchBtn").click(function () {
        searchPage();
    });

    /**
     * 上传图片监听事件
     */
    function setFileChangeListener() {
        $("#avater").change(function (e) {
            //将文件存储到formData中
            var file = e.target.files[0];
            var formData = new FormData();
            formData.append("part", file);
            fileUpload(formData);
        });
    }

    setFileChangeListener();

    /**
     * 文件上传
     */
    function fileUpload(formData) {
        $.ajax({
            type: "post",
            data: formData,
            url: "/upload",
            contentType: false,
            processData: false,
            success: function (response) {
                $(".bookimage").attr("src", response.data);
                $("#imgUrl").val(response.data);
            }
        });
    }


    /**
     * 添加书籍按钮点击事件
     */
    $("#addOrEdit").click(function () {
        addBook();
    });

    /**
     * 添加书籍
     */
    function addBook() {
        $.post("/book/addBook", $("#addorEidtForm").serialize(), function (response) {
            if (response.status == 20000) {
                //重置表单
                // resetForm();
                searchPage();
            }

        });
    }

    function resetForm() {
        $("#addorEidtForm").get(0).reset();
        $(".bookimage").attr("src", "");
    }

    function resetCheckBox() {
        $("#selectAll").prop("checked",false);
        bookIds = [];
    }

    //监听编辑按钮单击事件
    function setButtonClickListener() {
        $(".editBtn").click(function () {
            var bookId = $(this).parent().parent().attr("bookId");
            findById(bookId);
        });
        //添加书籍时重置表单
        $(".addBookBtn").click(function () {
            resetForm();
        });
        //点击删除按钮时 给全局变量bookId赋值
        $(".btn-warning").click(function () {
            var bookId = $(this).parent().parent().attr("bookId");
            //清空数组
            bookIds = [];
            //清空复选框
            $(".bookCheck").prop("checked", false);
            bookIds.push(bookId);
        });
        //点击确认删除按钮时 调用删除方法
        $(".delSure").click(function () {
            deleteById();
        });
        //当点击复选框时，判断复选框选中状态
        $(".bookCheck").click(function () {
            var bookId = $(this).parent().parent().attr("bookId");
            var prop = $(this).prop("checked");
            //如果页面上所有的复选框都被选中，则自动选中全选框
            var flag = $(".bookCheck:checked").length == pageSize;
            $("#selectAll").prop("checked", flag);

            if (prop) {
                //当复选框勾选中时 添加bookId到bookIds数组中
                bookIds.push(bookId);
            } else {
                bookIds.splice(bookIds.findIndex(function (item) {
                    return item == bookId;
                }), 1);
            }
        });
        //点击全选时，选中全部
        $("#selectAll").click(function () {
            //全选框的选中状态与其他框选中状态自动同步
            var flag = this.checked;
            $(".bookCheck").prop("checked", flag);
            if (flag) {
                bookIds = [];
                for (var i = 0; i < $(".bookInfo").length; i++) {
                    bookIds.push($($(".bookInfo")[i]).attr("bookId"));
                }
            }else{
                bookIds = [];
            }
        });


    }


    /**
     * 通过id删除书籍
     */
    function deleteById() {
        var ids = bookIds.join("A");
        $.get(`/book/deleteByBookIds?ids=${ids}`, function (response) {
            if (response.status == 20000) {
                searchPage();
            }
        })
    }

    /**
     * 通过id查询
     * @param bookId
     */
    function findById(bookId) {
        $.get(`/book/findByBookId/${bookId}`, function (response) {
            if (response.status == 20000) {
                findBookTypeByParentId(response.data.firstTypeId);
                setTimeout(function () {
                    setFormValues(response.data);
                }, 100)
                // $("#secondType").val(response.data.secondTypeId);

            }
        });
    }

    function setFormValues(book) {
        $("#bookName").val(book.bookName);
        $("#authorName").val(book.authorName);
        $("#firstType").val(book.firstTypeId);
        $("#secondType").val(book.secondTypeId);
        $("#description").val(book.description);
        $("#bookId").val(book.bookId);
        $("#imgUrl").val(book.imgUrl);
        $(".bookimage").attr("src", book.imgUrl);
    }


})