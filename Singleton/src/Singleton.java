public class Singleton {
        private static Singleton instance;
        private int field=0;

        private Singleton(){
            this.field++;
        }

        static{
            instance = new Singleton();
        }

        public int getField(){
            return this.field;
        }

        public static Singleton getInstance(){
            return instance;
        }
}
