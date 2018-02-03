package com.sxd.fs.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sxd.fs.bean.File;
import com.sxd.fs.dao.impl.BaseDao;
import com.sxd.fs.dao.impl.FileDaoImpl;
import com.sxd.fs.service.FileService;
import com.sxd.util.FileUtil;
import com.sxd.util.StringUtil;
import com.sxd.util.TimeUtil;

public class FileServiceImpl implements FileService {
	private File file = new File();
	private BaseDao<File> dao = null;
	
	public FileServiceImpl(File file) {
		this.file = file;
	}
	
	public FileServiceImpl() {
		//
	}
	
	@Override
	public int insert(java.io.File ioFile, String author) {
		//���ϴ��ļ��û�����Ϣ������Ĭ��ֵ
		if(StringUtil.isNullOrEmpty(author)) {
			author = "Unknow";				//�ò����費Ӧ�ñ�ִ�У����ϴ��ļ�ǰ��Ҫ�û���½
		}
		String fileName = ioFile.getName();
		file.setFileId(getMaxFileId() + 1);
		//�ļ���,"."֮ǰ�ַ���
		file.setFileName(fileName.substring(0, fileName.lastIndexOf(".")));
		file.setUploadAuthor(author);
		//�ļ���׺��
		file.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));
		file.setUploadTime(TimeUtil.getTime());				//ʱ������Ҫͳһ�����޸�TODO
		dao = new FileDaoImpl(file);
		dao.insert();
		return 0;
	}

	@Override
	public int delete(String downloadPath, int fileId) {
		file.setFileId(fileId);
		dao = new FileDaoImpl(file);
		File file= dao.select(new String[] {"fileId"}).get(0);
		dao.delete();
		FileUtil.deleteFile(downloadPath, file.getFileName() + "." + file.getFileType());
		return 0;
	}

	@Override
	public List<File> select(String...fields) {
		dao = new FileDaoImpl(file);
		return dao.select(fields);
	}
	
	public int getMaxFileId() {
		String sql = "SELECT MAX(fileId) FROM fs_file";
		dao = new FileDaoImpl(file);
		ResultSet rs = dao.select(sql);
		try {
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public boolean isFileAuthor(String user, int fileId) {
		file.setFileId(fileId);
		dao = new FileDaoImpl(file);
		String author = dao.select(new String[] {"fileId"}).get(0).getUploadAuthor();
		if(null != author && author.equals(user)) {
			return true;
		}
		return false;
	}
}
