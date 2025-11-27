# MyBatis-Fast
MyBatis增强封装工具 类似MyBatis-Plus  优点 动态SQL  不用XML 写代码
目前只针对Mysql  语法


################ 用法测试
        // 测试V3.1.0 版本
       String sqlD3 = "select * from home_dictionary";
       // 默认查 未分页总数和 分页列表数据
       PageResult pageResult1 = crud.getListCustomerByPageWithTotal(home_dictionary.class, sqlD3,1,10,null);
       writeLog("=======测试V3.1.0  普通格式 版本=============="+pageResult1.getTotal());
       // 指定 不查总数
       PageResult pageResult2 = crud.getListCustomerByPageWithTotal(home_dictionary.class, sqlD3,1,10,false,null);
       writeLog("=======测试V3.1 版本 普通格式 不查total=============="+pageResult2.getTotal());

       // 复查的查询  如果 上面的格式 不能实现 自动识别 from 位置  导致  total 和分页查询失败
       PagePara pagePara=new PagePara();
       pagePara.setClazz(home_dictionary.class);
       pagePara.setSqlSelect("*");
       pagePara.setSqlFrom("home_dictionary");
       pagePara.setSqlWhere("");
       pagePara.setPageNum(1);
       pagePara.setPageSize(10);
       pagePara.setNeedTotal(true);
       PageResult pageResult3 = crud.getListCustomerByPageWithTotal(pagePara);
       writeLog("=======测试V3.1 版本 实体参数格式 查total=============="+pageResult3.getTotal());

       pagePara.setNeedTotal(false);
       PageResult pageResult4 = crud.getListCustomerByPageWithTotal(pagePara);
       writeLog("=======测试V3.1 版本 实体参数格式 查不查 total=============="+pageResult4.getTotal());

        // 测试V1 版本
        String sqlD = "select * from home_dictionary";
        List<home_dictionary> listTypeD = crud.getListCustomer(home_dictionary.class, sqlD);

       writeLog("=======测试V1 版本=============="+listTypeD.get(0).getDICTIONARY_TYPE());


        // 测试V2 版本
        String sqlC = "select  * from cms_article";
        List<CmsArticle> listTypeC = crud.getListCustomer(CmsArticle.class, sqlC);



        writeLog("=================attr=================");
        Aatt   aatt=  crud.getByIdToMapper(Aatt.class, "74fc6a10-a02e-4678-a6ac-3e8ee582b04b", "id");
        aatt.setRemark("韩桂光1201");
        java.sql.Timestamp createdOn=new Timestamp(System.currentTimeMillis());
        aatt.setCreatedOn(createdOn);
       // crud.addByObject(aatt);
        crud.updateById(aatt);


        Aatt   aatt2=  crud.getByIdToMapper(Aatt.class, "74fc6a10-a02e-4678-a6ac-3e8ee582b04b", "id");


       String value="74fc6a10-a02e-4678-a6ac-3e8ee582b04b";
        String sqlSub = "select * from Aatt ";
        List<Aatt> listSubType = crud.getListCustomer(Aatt.class, sqlSub);

        sqlSub = "select a.id,b.id,a.uid,a.expires_Time from Aatt a join users b  on a.id=b.id   ";
        List<Aatt>  listSubType1 = crud.getListCustomer(Aatt.class, sqlSub);

         sqlSub = "select * from Aatt a join users b  on a.id=b.id   ";
        List<Aatt> listSubType2 = crud.getListCustomer(Aatt.class, sqlSub);

        Object newObj = crud.getByIdToMapper(Company.class, 1, "id");
        HomeDictionaryTest newObj2 = crud.getByIdToMapper(HomeDictionaryTest.class, "03aea939-ec03-46c3-b7ae-b9fc90226c40", "dictionarySid");
        Object newObj3 = crud.getByIdToMapper(HomeDictionaryTest.class, "03aea939-ec03-46c3-b7ae-b9fc90226c40", "dictionary_Sid");

       Object newObj4 = crud.getByIdToMapper(HomeDictionaryTest.class, "03aea939-ec03-46c3-b7ae-b9fc90226c40");


       Object newObj5 = crud.getByIdToMapper(HomeDictionaryTest2.class, "03aea939-ec03-46c3-b7ae-b9fc90226c40", "dictionary_Sid");
       Object newObj6 = crud.getByIdToMapper(HomeDictionaryTest2.class, "03aea939-ec03-46c3-b7ae-b9fc90226c40");


       crud.updateBySql(" home_Dictionary"," set dictionary_value='韩桂光'  where dictionary_Sid='03aea939-ec03-46c3-b7ae-b9fc90226c40'");

        String[] updateCol={"dictionary_Type_Desc","dictionarySid"};
        newObj2.setDictionarySid("123456");
        newObj2.setDictionary_Value("韩桂光测试");
        newObj2.setDictionary_Type_Desc("韩桂光描述");
        crud.updateByWhereSql(newObj2,"dictionary_Sid='03aea939-ec03-46c3-b7ae-b9fc90226c40'",updateCol);


       // crud.deleteById(HomeDictionaryTest.class,"03aea939-ec03-46c3-b7ae-b9fc90226c40");
        crud.deleteByWhereSql(HomeDictionaryTest.class,"dictionary_Sid='03aea939-ec03-46c3-b7ae-b9fc90226c40'");
