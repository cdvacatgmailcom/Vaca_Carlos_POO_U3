class Repositorio{
	
	private ArrayList <Equipo> equipos = new ArrayList<>();

	public static void addEquipo (Equipo equipo){
		equipos.add(equipo);	
	}

	public ArrayList <Equipo> getListEquipo (){
		return equipos;
	}
	
	public static void setListEquipo (ArrayList<Equipo> equipos){
		this.equipos = equipos;
	}

}