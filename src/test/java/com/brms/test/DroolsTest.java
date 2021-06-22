package com.brms.test;

import  com.brms.drools.entity.ComparisonOperatorEntity;
import com.brms.drools.entity.Order;
import com.brms.drools.entity.Student;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;


public class DroolsTest {
    @Test
    public  void  test1(){
        KieServices kieServices = KieServices.Factory.get();
        //创建一个Kie
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //获得一个Kie容器对象
        KieSession kieSession = kieClasspathContainer.newKieSession();
        //从Kiew容器对象中获取一个会话对象

        Order order=new Order(); //fact对象
        order.setOriginalPrice(500d);
        kieSession.insert(order);
        //将order对象放入工作内存当中
        System.out.println("优惠前原始价格：" + order.getOriginalPrice() +
                "，优惠后价格：" + order.getRealPrice());
        //下面要做的就是激活对象
        //激活规则：由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        kieSession.fireAllRules();

        kieSession.dispose();
        System.out.println("优惠前原始价格：" + order.getOriginalPrice() +
                "，优惠后价格：" + order.getRealPrice());






    }
    //测试比较操作符
    @Test
    public void test2(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();
        ComparisonOperatorEntity comparisonOperatorEntity = new ComparisonOperatorEntity();
        comparisonOperatorEntity.setNames("张三");
        ArrayList<String> list = new ArrayList<String>();
        list.add("zhangsan");
        list.add("lisi");
        comparisonOperatorEntity.setList(list);
        kieSession.insert(comparisonOperatorEntity);
        kieSession.fireAllRules();
        kieSession.dispose();


    }
    //测试执行制定规则
    @Test
    public void test3(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();
        ComparisonOperatorEntity comparisonOperatorEntity = new ComparisonOperatorEntity();
        comparisonOperatorEntity.setNames("zhangsan");
        ArrayList<String> list = new ArrayList<String>();
        list.add("zhangsan");
        list.add("lisi");
        comparisonOperatorEntity.setList(list);
        kieSession.insert(comparisonOperatorEntity);
        //针对全部
//        kieSession.fireAllRules();
        //执行某一个规则RuleNameEqualsAgendaFilter
        // 激活规则：由Drools框架自动进行规则匹配，如果规则匹配成功执行当前规则
//        kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("rule_comparison_memberOf"));
        //执行某些规则 执行某些名称为rule_开头的规则
        kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("rule_"));
        kieSession.dispose();



    }
     //测试Drools内置方法 update
    @Test
    public void test4(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();
        Student student=new Student();
        student.setAge(5);
        kieSession.insert(student);
        kieSession.fireAllRules();
        kieSession.dispose();


    }
    //测试Drools内置方法insert
    @Test
    public void test5(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();
        Student student=new Student();
        student.setAge(50);
        kieSession.insert(student);
        kieSession.fireAllRules();
        kieSession.dispose();


    }

}
