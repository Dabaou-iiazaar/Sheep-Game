class menu extends JPanel {
	public boolean ready=false;
    private boolean []keys, clickedPlay = false;
    private int delay = 0; frame = 0;
    private Image[8] pics;
    
	public GamePanel(){
		keys = new boolean[KeyEvent.KEY_LAST+1];
		addKeyListener(new moveListener());
        setSize(800,600);
        for(int i = 1; i<=8; i++){
            pics[i-1] = new ImageIcon("menuPics/Menu" + i).getImage();
        }
	}
	
    public void addNotify() {
        super.addNotify();
        requestFocus();
        ready = true;
    }
    
    public boolean clickedPlay(){
        //if(mousepos == rectpos){
            // return true
        // }
        return false;
    }

    public void paint(Graphics g){
        g.drawImage(pics[frame], 0, 0, null);
        if(delay %10 == 0){
            frame = (frame+1)%8
        }
        delay++;
    } 
}