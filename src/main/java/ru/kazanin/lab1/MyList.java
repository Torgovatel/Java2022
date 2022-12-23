package ru.kazanin.lab1;

/**
 * Класс-контейнер (список).<br>
 * Контейнер, реализующий функционал односвязного списка.
 * @param <T> тип элементов контейнера.
 * @author Andrey Kazanin
 * @version 1.0
 */
public class MyList<T> {
    /**
     * Класс узла списка.<br>
     * @param <T> тип данных для информации, хранимой в узле.
     */
    protected class Node<T> {
        /**
         * Информация текущего узла.
         */
        private T data;
        /**
         * Указатель на следующий узел.
         */
        private Node<T> next;

        /**
         * Конструктор узла по умолчанию.<br>
         * Заполняет поля звена как <b><i>null</i></b>
         * @see Node
         */
        public Node() {
            this.data = null;
            this.next = null;
        }
        /**
         * Конструктор узла по неполному содержимому.<br>
         * Устанавливает поле {@link #data} по переданным данные.<br>
         * Устанавливает поле {@link #next}  как <b><i>null</i></b>
         * @param NewData данные для записи в {@link #data}.
         * @see Node
         */
        public Node(T NewData) {
            this.data = NewData;
            this.next = null;
        }
        /**
         * Конструктор узла по полному содержимому.<br>
         * Устанавливает поля {@link #data}, {@link #next} по переданным данным.<br>
         * @param NewData данные для записи в узел.
         * @param NewNext указатель на следующий узел, после заданного.
         * @see Node
         */
        public Node(T NewData, Node<T> NewNext) {
            this.data = NewData;
            this.next = NewNext;
        }

        /**
         * Переопределение сравнения узлов по {@link Object#equals(Object)}.
         * @param elem объект для сравнения.
         * @return
         * <ul>
         *     <li><b><i>True</i></b> - узлы равны по полям.</li>
         *     <li><b><i>False</i></b> - узлы не равны по полям.</li>
         * </ul>
         */
        @Override
        public boolean equals(Object elem) {
            if (elem == this)
                return true;
            if (elem == null || elem.getClass() != this.getClass())
                return false;
            Node<T> tmp = (Node<T>)elem;
            return data.equals(tmp.data) &&
                    ((next == null && tmp.next == null) || next.equals(tmp.next));
        }
    }

    /**
     * Первый элемент списка.
     */
    private Node<T> head;
    /**
     * Количество элементов в списке.
     */
    private int size;

    /**
     * Конструктор списка по умолчанию.<br>
     * Устанавливает поле {@link #head} = <i>null</i><br>
     * Устанавливает поле {@link #size} = <i>0</i>
     */
    public MyList() {
        head = null;
        size = 0;
    }

    /**
     * Метод вставки элемента в начало списка.
     * @param value значение нового узла.
     */
    public void push(T value) {
        head = new Node<T>(value, head);
        size += 1;
    }
    /**
     * Метод удаления первого элемента списка.
     * @return
     * <ul>
     *      <li><b><i>True</i></b> - операция выполнена успешно</li>
     *      <li><b><i>False</i></b> - операция не выполнена</li>
     * </ul>
     */
    public boolean pop() {
        if (size == 0) {
            return false;
        }
        size -= 1;
        head = head.next;
        return true;
    }

    /**
     * Метод получения первого элемента списка.
     * @return значение из {@link #head}
     * @throws IndexOutOfBoundsException если значения в списке еще нет.
     */
    public final T peek() throws IndexOutOfBoundsException {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Список был пуст");
        }
        return head.data;
    }

    /**
     * Метод получения количества хранимых элементов.
     * @return {@link #size}.
     */
    public int size() {
        return size;
    }

    /**
     * Метод очистки полей списка.
     * Устанавливает поля в состояния, аналогичные {@link #MyList()}
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Переопределение приведения к строке {@link Object#toString()}<br>
     * @return строка вида [a, b, c], состоящая из элементов списка.
     */
    @Override
    public String toString() {
        String str = "[";
        Node<T> cur = head;
        boolean first = true;
        while (cur != null) {
            if(!first){
                str += ", ";
            }
            str += cur.data.toString();
            first = false;
            cur = cur.next;
        }
        str += ']';
        return str;
    }

    /**
     * Переопределение полного сравнения {@link Object#equals(Object)}.<br>
     * @param elem объект для сравнения.<br>
     * @return
     * <ul>
     *     <li><b><i>True</i></b> - если содержимое списков аналогично</li>
     *     <li><b><i>False</i></b> - в остальных случаях</li>
     * </ul>
     */
    @Override
    public boolean equals(Object elem) {
        if (elem == this)
            return true;
        if (elem == null || !elem.getClass().equals(this.getClass()))
            return false;
        if (((MyList<T>)elem).size != this.size)
            return false;
        Node<T> obj_cur = ((MyList<T>)elem).head;
        Node<T> this_cur = this.head;
        boolean good = true;
        while(good && obj_cur != null && this_cur != null) {
            good = obj_cur.equals(this_cur);
            obj_cur = obj_cur.next;
            this_cur = this_cur.next;
        }
        good = obj_cur == null && this_cur == null;
        return good;
    }
}
