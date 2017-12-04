package trabalhoOrientadaAObjetos;

import java.util.Comparator;

public class OrdenaRecebidas implements Comparator<Participante> {

	@Override
	public int compare(Participante n1, Participante n2) {
		// TODO Auto-generated method stub
		if(n1.getQntdRecebidas()>n2.getQntdRecebidas()) return -1;
		else if(n1.getQntdRecebidas()< n2.getQntdRecebidas()) return 1;
	return 0;
	}

	
}
