package com.saberhao.aopdemo.aspectjtest;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//@Aspect 声明切面类
@Aspect
public class TestAspectJ {


    private static final String INSTANCE_METHOD_CALL =
            "execution(* com.saberhao.aopdemo.MainActivity.test())";


    //1.使用@Pointcut定义切点，方便重复利用
    @Pointcut(INSTANCE_METHOD_CALL) public void pointCut() {
    }

    //2.切点调用前调用
    @Before(INSTANCE_METHOD_CALL)
    public void beforeNoUsePointCut(JoinPoint point) {
        Log.d("AOPDemo","@Before");
    }

    @Before("pointCut()") //3.该注解等价于 @Before(INSTANCE_METHOD_CALL)
    public void before(JoinPoint point) {
        Log.d("AOPDemo","@Before PointCut");
    }

    //4.切点调用后调用
    @After("pointCut()")
    public void after(JoinPoint point) {
        Log.d("AOPDemo","@After PointCut");
    }

    //5.切点有返回返回值后调用
    @AfterReturning(pointcut = "pointCut()", returning = "returnValue") //"pointcut" 同 "value"
    public void afterReturning(JoinPoint point, Object returnValue){
        Log.d("AOPDemo","@AfterReturning PointCut,return value is "+ returnValue);
    }

    //6.异常发生时调用，此时@AfterReturning不触发
    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void afterThrowing(Throwable ex) {
        Log.d("AOPDemo","@AfterThrowing，ex: " + ex.getMessage());
    }
    //7.使用@Around不调用切面函数，需要手动通过joinPoint.proceed()调用
//    @Around("pointCut()")
//    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//        Log.d("AOPDemo","@Around Before");
//        Object result = joinPoint.proceed();
//        Log.d("AOPDemo","@Around After");
//        return result;
//    }

}
