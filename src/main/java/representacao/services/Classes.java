//package representacao.services;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.lang.reflect.Modifier;
//
//public class Classes {
//	public static void main(String args[]) {  
//        try {
//        	
//        	//Classe
//        	Class c = Class.forName( "br.mestrado.representacao.services.ClasseService" );
//        	
//        	//Detalhamento dos métodos da classe
//            Method metodos[] = c.getDeclaredMethods();  
//            for (int i = 0; i < metodos.length; i++) {  
//            	Method m = metodos[i];  
//                System.out.println("nome = " + m.getName());  
//                System.out.println("membro da classe = " + m.getDeclaringClass());  
//                System.out.println("modificador = " + Modifier.toString( m.getModifiers() ));  
//                Class pvec[] = m.getParameterTypes();  
//  
//                for (int j = 0; j < pvec.length; j++)  
//                    System.out.println("parâmetro #" + j + " " + pvec[j]);  
//  
//                Class evec[] = m.getExceptionTypes();  
//                for (int j = 0; j < evec.length; j++)  
//                    System.out.println("exceção #" + j + " " + evec[j]);  
//  
//                System.out.println("tipo de retorno = " + m.getReturnType());  
//                System.out.println("-----");
//            }
//            
//            //Detalhamento dos construtores da calsse
//            Constructor ctorlist[] = c.getDeclaredConstructors();
//            
//            System.out.println("-----");
//            System.out.println("Construtores");
//            for (int i = 0; i < ctorlist.length; i++) {  
//                Constructor ct = ctorlist[i];  
//                System.out.println("nome = " + ct.getName());  
//                System.out.println("membro da classe = " + ct.getDeclaringClass());  
//          
//                Class pvec[] = ct.getParameterTypes();  
//                for (int j = 0; j < pvec.length; j++)  
//                    System.out.println("parâmetro #" + j + " " + pvec[j]);  
//  
//                Class evec[] = ct.getExceptionTypes();  
//                for (int j = 0; j < evec.length; j++)  
//                    System.out.println("exceção #" + j + " " + evec[j]);  
//            }  
//            
//            //Atributos
//            System.out.println("-----");
//            System.out.println("Atributos");
//            Field fieldlist[] = c.getDeclaredFields();  
//            for (int i = 0; i < fieldlist.length; i++) {  
//                Field fld = fieldlist[i];  
//                System.out.println("nome atributo = " + fld.getName());  
//                System.out.println("membro da classe = " + fld.getDeclaringClass());  
//                System.out.println("tipo = " + fld.getType());  
//                int mod = fld.getModifiers();  
//                System.out.println("modificadores = " + Modifier.toString(mod));  
//            }  
//
//            
////        	Testar o construtor da classe com valores
//        	
////            Class cls = Class.forName("java.lang.String");  
////            
////            boolean b1 = cls.isInstance(new Integer(37));  
////            System.out.println(b1);  
////              
////            boolean b2 = cls.isInstance(new java.lang.String("teste"));  
////            System.out.println(b2); 
//    }  
//        catch (Throwable e) {  
//            System.err.println(e);  
//        }  
//    }  
//}
