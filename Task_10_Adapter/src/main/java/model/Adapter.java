package model;

public class Adapter {
    StackX stack;
    public Adapter(int size){
        stack = new StackX(size);
    }
    public void newStackElement(long number)//добавление нового элемента
    {
        stack.push(number);

    }
    public void deleteStackElement(){
        stack.pop();
    }
    public long displayTopElement(){
        return stack.peek();
    }
    public String StackCheck(){
        if(stack.isEmpty()){
            return "Список пуст.";
        }
        if(stack.isFull()){
            return "Список имеет записи.";
        }
        else{
            return "Ошибка!";
        }
    }
    public String displayElements()//вывод элементов
    {
        return stack.display();
    }
    public String findElement(String el) {
        String elements = stack.display();
        String[] arrayOfStrings = elements.split(" ");

        for (int i = 0; i < arrayOfStrings.length; i++) {
            if (el.equals(arrayOfStrings[i])) {
                return arrayOfStrings[i];
            }
        }

        return "no";
    }

}
