import javax.swing.*;
import java.awt.*;
import java.util.Random;

class AlwaysOnTopModalDialog extends JDialog{
	
	private static final long serialVersionUID = 1L;

	AlwaysOnTopModalDialog(String title){
		super( getActiveWindow(), title, Dialog.ModalityType.APPLICATION_MODAL);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	AlwaysOnTopModalDialog(){
		super( getActiveWindow(), Dialog.ModalityType.APPLICATION_MODAL);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
		
	static Window getActiveWindow(){
		Window[] wins = Window.getWindows();
		for(int i = 0; i < wins.length; i++){
			if(wins[i].isActive())
				return wins[i];
		}
		return null;
	}
}

public class TimeConsumingTaskWithWorkerAndDialog {
	
	JDialog remindingDialog;
	
	int execTaskAndDisplayRemindingDialog(){
		WorkerForTimeConsumingTask worker = new WorkerForTimeConsumingTask();
		worker.execute();
		displayDialog();
		return worker.result;
	}
	
	void displayDialog(){
		remindingDialog = new AlwaysOnTopModalDialog();
		
		final int CLOUD_COMM_DIALOG_WIDTH = 300;
		final int CLOUD_COMM_DIALOG_HEIGHT = 220;
		remindingDialog.setSize( CLOUD_COMM_DIALOG_WIDTH, CLOUD_COMM_DIALOG_HEIGHT);
		 
		JPanel pane = new JPanel(new BorderLayout());
		remindingDialog.setContentPane(pane);
		JLabel label = new JLabel(" 正在执行耗时任务，请稍候 ...");
		pane.add(label,"Center");
		
		Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        int x = (width - CLOUD_COMM_DIALOG_WIDTH)/2;
        int y = (height - CLOUD_COMM_DIALOG_HEIGHT)/2;
        remindingDialog.setLocation(x,y);
        
        remindingDialog.setVisible(true);    
	}

	Integer doTheTimeConsumingTask(){
		int num  = 0;
		Random random = new Random();
	
		try{
			Thread.sleep(2000);
			num = random.nextInt(100);
		}catch(Exception e){
			e.printStackTrace();
		}
		return num;
	}
	class WorkerForTimeConsumingTask extends SwingWorker<Integer, Void>{
		Integer result = null;
		
		protected Integer doInBackground(){
			Integer response = doTheTimeConsumingTask();
			return response; // time consuming task return value
		}
		
		public void done(){
			try{
				result = get();	
			}catch(Exception e ){}
			
			remindingDialog.dispose();
		}
	}
	
	public static void main(String[] args){
		TimeConsumingTaskWithWorkerAndDialog task = new TimeConsumingTaskWithWorkerAndDialog();
		int res = task.execTaskAndDisplayRemindingDialog();
		System.out.println("res = " + res);
	}
	
}
	
	