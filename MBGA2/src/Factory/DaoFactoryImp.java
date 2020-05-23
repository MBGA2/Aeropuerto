package Factory;

import java.util.ArrayList;
import java.util.List;

import Transfer.seg.Transfer;

public class DaoFactoryImp {

	private static final DaoFactoryImp INSTANCE = new DaoFactoryImp();
	private List<DaoFactory> l = new ArrayList<DaoFactory>();

	private DaoFactoryImp() {

	}

	public static DaoFactoryImp getInstance() {
		return INSTANCE;
	}

	public DaoFactory parse(Transfer t) {
		for (DaoFactory f : l)
			if (f.parseCroncrete(t) != null)
				return f;
		return null;
	}

	public void addConcreteFactory(DaoFactory e) {
		l.add(e);
	}

}
