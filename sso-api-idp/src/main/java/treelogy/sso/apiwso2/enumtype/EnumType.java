package treelogy.sso.apiwso2.enumtype;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

@SuppressWarnings("rawtypes")
public class EnumType implements UserType, ParameterizedType {

    private Class enumClass;

    public EnumType() {
        super();
    }

    @SuppressWarnings("unchecked")
    public void setParameterValues(Properties parameters) {
        String enumClassName = parameters.getProperty("enumClassName");
        try {
            enumClass = (Class) Class.forName(enumClassName);
        } catch (ClassNotFoundException e) {
            throw new HibernateException("Enum class not found ", e);
        }

    }

    public int[] sqlTypes() {
        return new int[] { Types.OTHER };
    }

    public Class returnedClass() {
        return enumClass;
    }

    public boolean equals(Object x, Object y) throws HibernateException {
        return x == y;
    }

    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
        String pgObject = rs.getString(names[0]);
        try {
            return Enum.valueOf(enumClass, pgObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, Types.OTHER);
        } else {
            st.setObject(index,((Enum) value), Types.OTHER);
        }
    }

    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    public boolean isMutable() {
        return false;
    }

    public Serializable disassemble(Object value) throws HibernateException {
        return (Enum) value;
    }

    public Object assemble(Serializable cached, Object owner)
            throws HibernateException {
        return cached;
    }

    public Object replace(Object original, Object target, Object owner)
            throws HibernateException {
        return original;
    }

    @SuppressWarnings("unchecked")
    public Object fromXMLString(String xmlValue) {
        return Enum.valueOf(enumClass, xmlValue);
    }

    public String objectToSQLString(Object value) {
        return '\'' + ((Enum) value).name() + '\'';
    }

    public String toXMLString(Object value) {
        return ((Enum) value).name();
    }

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		
	}
}