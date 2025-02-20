package model;

public class StackX {
    private int maxSize;//размер массива
    private long[] stackArray;
    private int top;//вершина стэка
    public StackX(int s)//конструктор
    {
        maxSize = s;//установление размера массива
        stackArray = new long[maxSize];//создание массива
        top = -1;//пока нет элементов
    }
    public void push(long j)//положить элемент наверх стопки
    {
        stackArray[++top] = j;//увеличить вверх, вставить элемент
    }
    public long pop()//взять элемент с вершины стопки
    {
        return stackArray[top--];//доступ к элементу уменьщение верхнего значения
    }
    public long peek()//заглянуть наверх стека
    {
        return stackArray[top];
    }
    public boolean isEmpty()//истина, если стек пуст
    {
        return (top == -1);
    }
    public boolean isFull()//истина, если стек полон
    {
        return (top == maxSize-1);
    }
    public String display()//демонстрация
    {
        String str="";

        for(int j=top; j>=0; j--)
        {
            long value = stackArray[j];
            str+= value+ " ";
        }
        return str;
    }

}
