package com.example.demo.designMode.command;

/**
 * @Author: hanDa
 * @Date: 2020/12/24 14:07
 * @Version:1.0
 * @Description:
 */
public class CommandMode {
    private interface Command {
        /**
         * execute
         */
        void exec();
    }

    private class ConcreteCommand implements Command {
        private Receiver receiver;

        public ConcreteCommand(Receiver receiver) {
            this.receiver = receiver;
        }

        @Override
        public void exec() {
            this.receiver.doSomethings();
        }
    }

    private class Receiver {
        public void doSomethings() {
            System.out.println("do some things! ");
        }
    }

    private class Invoker{
        private Command command;

        public Invoker(Command command) {
            this.command = command;
        }
        public void  action(){
            this.command.exec();
        }
    }

    public static void main(String[] args) {
        CommandMode commandMode = new CommandMode();
        Receiver receiver = commandMode.new Receiver();
        Command command = commandMode.new ConcreteCommand(receiver);
        Invoker invoker = commandMode.new Invoker(command);
        invoker.action();
    }
}