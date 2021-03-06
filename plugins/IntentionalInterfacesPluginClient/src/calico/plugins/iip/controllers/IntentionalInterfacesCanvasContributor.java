package calico.plugins.iip.controllers;

import calico.components.CCanvas;
import calico.controllers.CCanvasController;
import calico.plugins.iip.components.CIntentionCell;

public class IntentionalInterfacesCanvasContributor implements CCanvas.ContentContributor
{
	public static IntentionalInterfacesCanvasContributor getInstance()
	{
		return INSTANCE;
	}

	public static void initialize()
	{
		INSTANCE = new IntentionalInterfacesCanvasContributor();
	}

	private static IntentionalInterfacesCanvasContributor INSTANCE;

	private IntentionalInterfacesCanvasContributor()
	{
		CCanvasController.addContentContributor(this);
	}

	public void notifyContentChanged(long canvasId)
	{
		CCanvasController.notifyContentChanged(this, canvasId);
	}

	@Override
	public void contentChanged(long canvas_uuid)
	{
		CIntentionCell cell = CIntentionCellController.getInstance().getCellByCanvasId(canvas_uuid);
		if (cell == null)
		{
			return;
		}

		IntentionGraphController.getInstance().contentChanged(canvas_uuid);
	}

	@Override
	public void clearContent(long canvas_uuid)
	{
		CCanvasLinkController.getInstance().clearLinks(canvas_uuid);

		long cellId = CIntentionCellController.getInstance().getCellByCanvasId(canvas_uuid).getId();
		CIntentionCellController.getInstance().clearCell(cellId);
	}
}
