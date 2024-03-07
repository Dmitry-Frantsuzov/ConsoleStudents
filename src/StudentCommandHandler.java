public class StudentCommandHandler {
    public void processComand(Command command) {
        System.out.println("Обработка команды." +
                " действие: " + command.getAction().name()
                + ", данные: " + command.getData());
    }
}
