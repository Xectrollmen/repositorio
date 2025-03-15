import java.util.*;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese una expresión matemática:");
        String expresion = sc.nextLine();

        if (!expresion.matches("[a-zA-Z0-9+\\-*/^√(). ]+")) {
            System.out.println("Expresión inválida. Solo se permiten letras, números y operadores.");
            return;
        }
        List<String> postfija = ArbolExpresion.infijaAPostfija(expresion);
        System.out.println("Notación postfija: " + postfija);

        Nodo raiz = ArbolExpresion.construirArbol(postfija);

        Set<String> variables = new HashSet<>();
        for (String token : postfija) {
            if (!ArbolExpresion.esOperador(token) && !token.matches("\\d+(\\.\\d+)?")) {
                variables.add(token);
            }
        }

        Map<String, Double> valores = new HashMap<>();
        for (String var : variables) {
            Double valor = null;
            while (valor == null) {
                try {
                    System.out.print("Ingrese el valor de " + var + ": ");
                    valor = Double.parseDouble(sc.nextLine().trim());
                    valores.put(var, valor);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Por favor ingrese un número válido para '" + var + "'.");
                }
            }
        }

        System.out.print("Recorrido Inorden: ");
        Recorridos.inorden(raiz);
        System.out.println();

        System.out.print("Recorrido Postorden: ");
        Recorridos.postorden(raiz);
        System.out.println();

        double resultado = Evaluador.evaluar(raiz, valores);
        System.out.println("Resultado de la expresión: " + resultado);
    }
}