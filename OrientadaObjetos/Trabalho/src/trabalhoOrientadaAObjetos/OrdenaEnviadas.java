package trabalhoOrientadaAObjetos;

import java.util.Comparator;

public class OrdenaEnviadas implements Comparator<Participante> {
		@Override
		public int compare(Participante n1, Participante n2) {

			if(n1.getQntdEnviadas()>n2.getQntdEnviadas()) return -1;
			else if(n1.getQntdEnviadas()< n2.getQntdEnviadas()) return 1;
		return 0;
		}


}
