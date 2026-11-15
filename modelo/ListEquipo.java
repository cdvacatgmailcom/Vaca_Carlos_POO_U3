class ListEquipo{
	
	private ArrayList <Equipo> equipos = new ArrayList<>();

	public static void addListEquipo (Equipo equipo){
		equipos.add(equipo);	
	}

	public static ArrayList <Equipo> getListEquipo (){
		return equipos;
	}
	
	public static void setListEquipo (ArrayList<Equipo> equipos){
		this.equipos = equipos;
	}

}