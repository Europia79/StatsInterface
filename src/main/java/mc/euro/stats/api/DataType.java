package mc.euro.stats.api;

import java.util.Date;

/**
 *
 * @author Nikolai
 */
public interface DataType<E> {
    DataType<String> STRING = new BasicDataType<>(String.class);
    DataType<Integer> INTEGER = new BasicDataType<>(Integer.class);
    DataType<Double> DOUBLE = new BasicDataType<>(Double.class);
    DataType<Float> FLOAT = new BasicDataType<>(Float.class);
    DataType<Long> LONG = new BasicDataType<>(Long.class);
    DataType<Date> TIMESTAMP = new BasicDataType<>(Date.class);
    DataType<Boolean> BOOLEAN = new BasicDataType<>(Boolean.class);
    DataType<Byte[]> BYTE_ARRAY = new BasicDataType<>(Byte[].class);

    Class<E> getDataClass();

    final class BasicDataType<E> implements DataType<E> {
        private final Class<E> clazz;

        public BasicDataType(Class<E> clazz) {
            this.clazz = clazz;
        }

        @Override
        public Class<E> getDataClass() {
            return clazz;
        }
    }
}
