public class Recorridos {
    public static void inorden(Nodo nodo) {
        if (nodo != null) {
            if (nodo.izquierdo != null) System.out.print("( ");
            inorden(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            inorden(nodo.derecho);
            if (nodo.derecho != null) System.out.print(") ");
        }
    }

    public static void postorden(Nodo nodo) {
        if (nodo != null) {
            postorden(nodo.izquierdo);
            postorden(nodo.derecho);
            System.out.print(nodo.valor + " ");
        }
    }
}