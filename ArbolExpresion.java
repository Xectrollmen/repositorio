import java.util.*;

public class ArbolExpresion {
    private static final Map<String, Integer> precedencia = new HashMap<>();

    static {
        precedencia.put("+", 1);
        precedencia.put("-", 1);
        precedencia.put("*", 2);
        precedencia.put("/", 2);
        precedencia.put("^", 3);
        precedencia.put("√", 3);
    }

    public static List<String> infijaAPostfija(String expr) {
        Stack<String> operadores = new Stack<>();
        List<String> salida = new ArrayList<>();
        StringTokenizer tokens = new StringTokenizer(expr, "+-*/^√() ", true);

        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken().trim();
            if (token.isEmpty()) continue;

            if (esOperador(token)) {
                while (!operadores.isEmpty() && !operadores.peek().equals("(")
                        && precedencia.get(token) <= precedencia.get(operadores.peek())) {
                    salida.add(operadores.pop());
                }
                operadores.push(token);
            } else if (token.equals("(")) {
                operadores.push(token);
            } else if (token.equals(")")) {
                while (!operadores.peek().equals("(")) {
                    salida.add(operadores.pop());
                }
                operadores.pop();
            } else {
                salida.add(token);
            }
        }

        while (!operadores.isEmpty()) {
            salida.add(operadores.pop());
        }

        return salida;
    }

    public static boolean esOperador(String token) {
        return precedencia.containsKey(token);
    }

    public static Nodo construirArbol(List<String> postfija) {
        Stack<Nodo> pila = new Stack<>();

        for (String token : postfija) {
            Nodo nodo = new Nodo(token);
            if (esOperador(token)) {
                nodo.derecho = pila.pop();
                if (!token.equals("√")) {
                    nodo.izquierdo = pila.pop();
                }
            }
            pila.push(nodo);
        }

        return pila.pop();
    }
}