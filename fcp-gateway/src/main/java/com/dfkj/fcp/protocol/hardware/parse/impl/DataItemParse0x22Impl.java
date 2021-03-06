package com.dfkj.fcp.protocol.hardware.parse.impl;

import com.dfkj.fcp.core.constant.EDeviceStatus;
import com.dfkj.fcp.core.constant.EDeviceType;
import com.dfkj.fcp.core.constant.EValueStatus;
import com.dfkj.fcp.core.util.ByteArray;
import com.dfkj.fcp.core.vo.DataMessageItem;
import com.dfkj.fcp.protocol.hardware.parse.DataItemParseAnnotation;
import com.dfkj.fcp.protocol.hardware.parse.IDataItemParseGtw1P1;

/**
 * 0x22 风向数据
 * @author songfei
 * @date 2016-05-26
 */
@DataItemParseAnnotation(dataTypeId = 0x22)
public class DataItemParse0x22Impl implements IDataItemParseGtw1P1 {

	public DataMessageItem[] parse(ByteArray content,int channelNo, int positionNo) {
		DataMessageItem item = new DataMessageItem();
		
		short windDirct = content.getShortAt(0);
		content.removeAt(0, 2);
		
		item.setValue(windDirct);
		item.setDevType(EDeviceType.WIND_DIRECTION);
		item.setChannelNo(channelNo);
		item.setPositionNo(positionNo);
		item.setDevStatus(EDeviceStatus.NORMAL);
		item.setValStatus(EValueStatus.VALID);
		if (windDirct > 360) {
			item.setValStatus(EValueStatus.INVALID);
		}
		
		return new DataMessageItem[] { item };
	}

}
