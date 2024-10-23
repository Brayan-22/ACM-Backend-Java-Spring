package com.acm.programacionaspectos.aop;


import com.acm.programacionaspectos.persistence.entity.Cliente;
import com.acm.programacionaspectos.persistence.entity.Producto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

@Component
@Aspect
public class LoginAspect {

    private static final Logger log = LoggerFactory.getLogger(LoginAspect.class);

    public void envioMail(){
        System.out.println("envioMailDeErrores");
    }

    @Pointcut(value = "execution(* save*(*))")
    private void saves(){}

    @Pointcut(value = "execution(* com.acm.programacionaspectos.persistence.dao.impl.*.*save(..))")
    private void all(){}


    /*
    * Aspecto para simular un sistema de autentificacion mediante aop, lógica antes del metodo
    * */
    //@Before(value = "execution(* com.acm.programacionaspectos.persistence.dao.impl.ClienteDAO.save(*))")
    //@Before(value = "execution(* save*(*))")
    @Before("saves()")
    public void beforeSave(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Cliente) {
                Cliente cliente = (Cliente) arg;
                System.out.println("Cliente desde aop: "+cliente);
            }
            if (arg instanceof Producto){
                Producto producto = (Producto) arg;
                System.out.println("Producto desde aop: "+producto);
            }
        }
        System.out.println("El usuario está logeado");
        System.out.println("El usuario tiene los permisos necesarios");
    }
    //@After(value = "execution(* save(*))")
    /*
     * Aspecto para simular un sistema de autentificacion mediante aop, lógica antes del metodo
     * */
    @After("all()")
    public void afterSave(){
        System.out.println("Usuario deslogeado");
    }
    /*
     * Aspecto para atrapar el retorno de un metodo y añadir funcionalidades
     * */
    @AfterReturning(value = "execution(* com.acm.programacionaspectos.persistence.dao.impl.ClienteDAO.*findAll*()))",returning = "lista")
    public void afterList(List<Cliente> lista){
        for (Cliente cliente : lista) {
            if (cliente.nombre().startsWith("F")){
                System.out.println(cliente.nombre());
            }
        }
    }
    /*
     * Aspecto para simular un envio de notificacion de errores en caso de lanzar una excepcion
     * */
    @AfterThrowing(value = "execution(* com.acm.programacionaspectos.persistence.dao.impl.*.*save(..))",throwing = "throwable")
    public void afterTrow(Throwable throwable){
        if (throwable instanceof SQLException){
            SQLException sqlException = (SQLException) throwable;
            System.out.println(sqlException.getMessage()+ "Error de sql");
        }
        envioMail();
    }

    /*
     * Aspecto para auditoria y control de tiempo de ejecución de un metodo
     * */
    @Around(value = "execution(* com.acm.programacionaspectos.persistence.dao.impl.*.*findById*(*))")
    public Object aroundFindById(ProceedingJoinPoint joinPoint) throws Throwable{
        long inicio = System.currentTimeMillis();
        var o = joinPoint.proceed();
        long fin = System.currentTimeMillis();
        log.info("Tiempo de ejecucion del método {}: {}s",joinPoint.getSignature().getName(), Duration.ofMillis(fin-inicio).toSeconds());
        return o;
    }

    /*
     * Notese que no estamos modificando el código interno de los lugares donde serán aplicados los aspectos, esta es una de las grandes funcionalidades de aop, en conjunto con los principios SOLID
     */

}
