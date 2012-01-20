package calico.components.piemenu.groups;

import java.awt.Rectangle;

import calico.Calico;
import calico.components.piemenu.PieMenuButton;
import calico.controllers.CCanvasController;
import calico.controllers.CGroupController;
import calico.controllers.CStrokeController;
import calico.iconsets.CalicoIconManager;
import calico.inputhandlers.InputEventInfo;

public class GroupShrinkToContentsButton extends PieMenuButton
{

	public static int SHOWON = PieMenuButton.SHOWON_SCRAP_CREATE | PieMenuButton.SHOWON_SCRAP_MENU;
	long guuid;
	
	public GroupShrinkToContentsButton(long uuid)
	{
		super("group.rectify");
		guuid = uuid;
	}
	
	public void onClick(InputEventInfo ev)
	{
		super.onClick(ev);
		if (CGroupController.exists(guuid))
		{
//			CGroupController.set_permanent(guuid, true);
			Rectangle bounds = CGroupController.groupdb.get(guuid).getBoundsOfContents();
			CGroupController.makeRectangle(guuid, bounds.x, bounds.y, bounds.width, bounds.height);
//			CGroupController.shrink_to_contents(guuid);
		}
		else if (CStrokeController.exists(guuid))
		{
			long new_uuid = Calico.uuid();
			CStrokeController.makeShrunkScrap(guuid, new_uuid);
			CGroupController.set_permanent(new_uuid, true);
//			long groupUUID = CStrokeController.makeScrap(guuid);
//			CGroupController.shrink_to_contents(groupUUID);
		}
		ev.stop();
		
		Calico.logger.debug("CLICKED SHRINK WRAP BUTTON");
	}
	
	
}