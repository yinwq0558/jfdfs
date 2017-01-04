package fdfs.clients.jfdfs;

import java.io.IOException;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.DownloadCallback;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.csource.fastdfs.UploadCallback;

public class Jfdfs {
	private final TrackerClient tracker;
	private final TrackerServer trackerServer;
	private StorageServer storageServer = null;
	private final JfdfsPool dataSource;
	public Jfdfs(JfdfsPool dataSource) throws IOException {
		super();
		this.tracker = new TrackerClient();
		this.trackerServer = tracker.getConnection();
		this.dataSource = dataSource;
	}
	
	public void close() {
		this.dataSource.returnObject(this);
	}
	
	public String[] upload_file(String local_filename) throws IOException, MyException {
    	int pos = local_filename.lastIndexOf(".");
    	String file_ext_name = local_filename.substring(pos + 1);
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.upload_file(local_filename, file_ext_name, null);
	}
	public String[] upload_file(String local_filename, 
		       NameValuePair[] meta_list) throws IOException, MyException {
    	int pos = local_filename.lastIndexOf(".");
    	String file_ext_name = local_filename.substring(pos + 1);
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.upload_file(local_filename, file_ext_name, meta_list);
	}
	public String[] upload_file(String local_filename, String file_ext_name) throws IOException, MyException {
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.upload_file(local_filename, file_ext_name, null);
	}
	
	/**
	* upload file to storage server (by file name)
	* @param local_filename local filename to upload
	* @param file_ext_name file ext name, do not include dot(.), null to extract ext name from the local filename
	* @param meta_list meta info array
	* @return  2 elements string array if success:<br>
	*           <ul><li>results[0]: the group name to store the file </li></ul>
	*           <ul><li>results[1]: the new created filename</li></ul>
	*         return null if fail
	*/
	public String[] upload_file(String local_filename, String file_ext_name, 
	       NameValuePair[] meta_list) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.upload_file(local_filename, file_ext_name, meta_list);
	}

	/**
	* upload file to storage server (by file buff)
	* @param file_buff file content/buff
	* @param offset start offset of the buff
	* @param length the length of buff to upload
	* @param file_ext_name file ext name, do not include dot(.)
	*	@param meta_list meta info array
	* @return  2 elements string array if success:<br>
	*           <ul><li>results[0]: the group name to store the file</li></ul>
	*           <ul><li>results[1]: the new created filename</li></ul>
	*         return null if fail
	*/
	public String[] upload_file(byte[] file_buff,	int offset, int length, String file_ext_name, 
	       NameValuePair[] meta_list) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.upload_file(file_buff,	offset, length, file_ext_name, meta_list);
	}
	
	/**
	* upload file to storage server (by file buff)
	* @param group_name the group name to upload file to, can be empty
	* @param file_buff file content/buff
	* @param offset start offset of the buff
	* @param length the length of buff to upload
	* @param file_ext_name file ext name, do not include dot(.)
	*	@param meta_list meta info array
	* @return  2 elements string array if success:<br>
	*           <ul><li>results[0]: the group name to store the file</li></ul>
	*           <ul><li>results[1]: the new created filename</li></ul>
	*         return null if fail
	*/
	public String[] upload_file(String group_name, byte[] file_buff, int offset, int length,	
	       String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.upload_file(group_name, file_buff, offset, length,	
			       file_ext_name, meta_list);
	}
	
	/**
	* upload file to storage server (by file buff)
	* @param file_buff file content/buff
	* @param file_ext_name file ext name, do not include dot(.)
	*	@param meta_list meta info array
	* @return  2 elements string array if success:<br>
	*           <ul><li>results[0]: the group name to store the file</li></ul>
	*           <ul><li>results[1]: the new created filename</li></ul>
	*         return null if fail
	*/
	public String[] upload_file(byte[] file_buff, String file_ext_name, 
	       NameValuePair[] meta_list) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.upload_file(file_buff, file_ext_name, meta_list);
	}
	
	/**
	* upload file to storage server (by file buff)
	* @param group_name the group name to upload file to, can be empty
	* @param file_buff file content/buff
	* @param file_ext_name file ext name, do not include dot(.)
	*	@param meta_list meta info array
	* @return  2 elements string array if success:<br>
	*           <ul><li>results[0]: the group name to store the file</li></ul>
	*           <ul><li>results[1]: the new created filename</li></ul>
	*         return null if fail
	*/
	public String[] upload_file(String group_name, byte[] file_buff,	
	       String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.upload_file(group_name, file_buff,	file_ext_name, meta_list);
	}
	
	
	/**
	* upload file to storage server (by callback)
	* @param group_name the group name to upload file to, can be empty
	* @param file_size the file size
	* @param callback the write data callback object
	* @param file_ext_name file ext name, do not include dot(.)
	*	@param meta_list meta info array
	* @return  2 elements string array if success:<br>
	*           <ul><li>results[0]: the group name to store the file</li></ul>
	*           <ul><li>results[1]: the new created filename</li></ul>
	*         return null if fail
	*/
	public String[] upload_file(String group_name, long file_size, UploadCallback callback, 
	       String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.upload_file(group_name, file_size, callback, file_ext_name, meta_list);
	}
	
	/**
	* upload file to storage server (by file name, slave file mode)
	* @param group_name the group name of master file
	* @param master_filename the master file name to generate the slave file
	* @param prefix_name the prefix name to generate the slave file
	* @param local_filename local filename to upload
	* @param file_ext_name file ext name, do not include dot(.), null to extract ext name from the local filename
	* @param meta_list meta info array
	* @return  2 elements string array if success:<br>
	*           <ul><li>results[0]: the group name to store the file </li></ul>
	*           <ul><li>results[1]: the new created filename</li></ul>
	*         return null if fail
	*/
	public String[] upload_file(String group_name, String master_filename, String prefix_name, 
         String local_filename, String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.upload_file(group_name, master_filename, prefix_name, 
		         local_filename, file_ext_name, meta_list);
	}
	
	/**
	* upload file to storage server (by file buff, slave file mode)
	* @param group_name the group name of master file
	* @param master_filename the master file name to generate the slave file
	* @param prefix_name the prefix name to generate the slave file
	* @param file_buff file content/buff
	* @param file_ext_name file ext name, do not include dot(.)
	*	@param meta_list meta info array
	* @return  2 elements string array if success:<br>
	*           <ul><li>results[0]: the group name to store the file</li></ul>
	*           <ul><li>results[1]: the new created filename</li></ul>
	*         return null if fail
	*/
	public String[] upload_file(String group_name, String master_filename, String prefix_name, 
	       byte[] file_buff, String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.upload_file(group_name, master_filename, prefix_name, 
			       file_buff, file_ext_name, meta_list);
	}
	
	/**
	* upload file to storage server (by file buff, slave file mode)
	* @param group_name the group name of master file
	* @param master_filename the master file name to generate the slave file
	* @param prefix_name the prefix name to generate the slave file
	* @param file_buff file content/buff
	* @param offset start offset of the buff
	* @param length the length of buff to upload
	* @param file_ext_name file ext name, do not include dot(.)
	*	@param meta_list meta info array
	* @return  2 elements string array if success:<br>
	*           <ul><li>results[0]: the group name to store the file</li></ul>
	*           <ul><li>results[1]: the new created filename</li></ul>
	*         return null if fail
	*/
	public String[] upload_file(String group_name, String master_filename, String prefix_name, 
	       byte[] file_buff, int offset, int length, String file_ext_name, 
	       NameValuePair[] meta_list) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.upload_file(group_name, master_filename, prefix_name, 
			       file_buff, offset, length, file_ext_name, meta_list);
	}

	/**
	* upload file to storage server (by callback, slave file mode)
	* @param group_name the group name to upload file to, can be empty
	* @param master_filename the master file name to generate the slave file
	* @param prefix_name the prefix name to generate the slave file
	* @param file_size the file size
	* @param callback the write data callback object
	* @param file_ext_name file ext name, do not include dot(.)
	*	@param meta_list meta info array
	* @return  2 elements string array if success:<br>
	*           <ul><li>results[0]: the group name to store the file</li></ul>
	*           <ul><li>results[1]: the new created filename</li></ul>
	*         return null if fail
	*/
	public String[] upload_file(String group_name, String master_filename, 
	       String prefix_name, long file_size, UploadCallback callback, 
	       String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.upload_file(group_name, master_filename, 
			       prefix_name, file_size, callback, file_ext_name, meta_list);
	}
	
	/**
	* upload appender file to storage server (by file name)
	* @param local_filename local filename to upload
	* @param file_ext_name file ext name, do not include dot(.), null to extract ext name from the local filename
	* @param meta_list meta info array
	* @return  2 elements string array if success:<br>
	*           <ul><li>results[0]: the group name to store the file </li></ul>
	*           <ul><li>results[1]: the new created filename</li></ul>
	*         return null if fail
	*/
	public String[] upload_appender_file(String local_filename, String file_ext_name, 
	       NameValuePair[] meta_list) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.upload_appender_file(local_filename, file_ext_name, meta_list);
	}

	/**
	* upload appender file to storage server (by file buff)
	* @param file_buff file content/buff
	* @param offset start offset of the buff
	* @param length the length of buff to upload
	* @param file_ext_name file ext name, do not include dot(.)
	*	@param meta_list meta info array
	* @return  2 elements string array if success:<br>
	*           <ul><li>results[0]: the group name to store the file</li></ul>
	*           <ul><li>results[1]: the new created filename</li></ul>
	*         return null if fail
	*/
	public String[] upload_appender_file(byte[] file_buff,	int offset, int length, String file_ext_name, 
	       NameValuePair[] meta_list) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.upload_appender_file(file_buff, offset, length, file_ext_name, meta_list);
	}
	
	/**
	* upload appender file to storage server (by file buff)
	* @param group_name the group name to upload file to, can be empty
	* @param file_buff file content/buff
	* @param offset start offset of the buff
	* @param length the length of buff to upload
	* @param file_ext_name file ext name, do not include dot(.)
	*	@param meta_list meta info array
	* @return  2 elements string array if success:<br>
	*           <ul><li>results[0]: the group name to store the file</li></ul>
	*           <ul><li>results[1]: the new created filename</li></ul>
	*         return null if fail
	*/
	public String[] upload_appender_file(String group_name, byte[] file_buff, int offset, int length,	
	       String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.upload_appender_file(group_name, file_buff, offset, length,	
			       file_ext_name, meta_list);
	}
	
	/**
	* upload appender file to storage server (by file buff)
	* @param file_buff file content/buff
	* @param file_ext_name file ext name, do not include dot(.)
	*	@param meta_list meta info array
	* @return  2 elements string array if success:<br>
	*           <ul><li>results[0]: the group name to store the file</li></ul>
	*           <ul><li>results[1]: the new created filename</li></ul>
	*         return null if fail
	*/
	public String[] upload_appender_file(byte[] file_buff, String file_ext_name, 
	       NameValuePair[] meta_list) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.upload_appender_file(file_buff, file_ext_name, meta_list);
	}
	
	/**
	* upload appender file to storage server (by file buff)
	* @param group_name the group name to upload file to, can be empty
	* @param file_buff file content/buff
	* @param file_ext_name file ext name, do not include dot(.)
	*	@param meta_list meta info array
	* @return  2 elements string array if success:<br>
	*           <ul><li>results[0]: the group name to store the file</li></ul>
	*           <ul><li>results[1]: the new created filename</li></ul>
	*         return null if fail
	*/
	public String[] upload_appender_file(String group_name, byte[] file_buff,	
	       String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.upload_appender_file(group_name, file_buff,	
			       file_ext_name, meta_list);
	}
	
	/**
	* upload appender file to storage server (by callback)
	* @param group_name the group name to upload file to, can be empty
	* @param file_size the file size
	* @param callback the write data callback object
	* @param file_ext_name file ext name, do not include dot(.)
	*	@param meta_list meta info array
	* @return  2 elements string array if success:<br>
	*           <ul><li>results[0]: the group name to store the file</li></ul>
	*           <ul><li>results[1]: the new created filename</li></ul>
	*         return null if fail
	*/
	public String[] upload_appender_file(String group_name, long file_size, UploadCallback callback, 
	       String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.upload_appender_file(group_name, file_size, callback, 
			       file_ext_name, meta_list);
	}

	/**
	* append file to storage server (by file name)
	* @param group_name the group name of appender file
	* @param appender_filename the appender filename
	* @param local_filename local filename to append
	* @return 0 for success, != 0 for error (error no)
	*/
	public int append_file(String group_name, String appender_filename, String local_filename) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.append_file(group_name, appender_filename, local_filename);
	}
	
	/**
	* append file to storage server (by file buff)
	* @param group_name the group name of appender file
	* @param appender_filename the appender filename
	* @param file_buff file content/buff
	* @return 0 for success, != 0 for error (error no)
	*/
	public int append_file(String group_name, String appender_filename, byte[] file_buff) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.append_file(group_name, appender_filename, file_buff);
	}
	
	/**
	* append file to storage server (by file buff)
	* @param group_name the group name of appender file
	* @param appender_filename the appender filename
	* @param file_buff file content/buff
	* @param offset start offset of the buff
	* @param length the length of buff to append
	* @return 0 for success, != 0 for error (error no)
	*/
	public int append_file(String group_name, String appender_filename, 
	       byte[] file_buff, int offset, int length) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.append_file(group_name, appender_filename, 
			       file_buff, offset, length);
	}

	/**
	* append file to storage server (by callback)
	* @param group_name the group name to append file to
	* @param appender_filename the appender filename
	* @param file_size the file size
	* @param callback the write data callback object
	* @return 0 for success, != 0 for error (error no)
	*/
	public int append_file(String group_name, String appender_filename, 
	       long file_size, UploadCallback callback) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.append_file(group_name, appender_filename, 
			       file_size, callback);
	}

	/**
	* modify appender file to storage server (by file name)
	* @param group_name the group name of appender file
	* @param appender_filename the appender filename
	* @param file_offset the offset of appender file
	* @param local_filename local filename to append
	* @return 0 for success, != 0 for error (error no)
	*/
	public int modify_file(String group_name, String appender_filename, 
			long file_offset, String local_filename) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.modify_file(group_name, appender_filename, 
				file_offset, local_filename);
	}
	
	/**
	* modify appender file to storage server (by file buff)
	* @param group_name the group name of appender file
	* @param appender_filename the appender filename
	* @param file_offset the offset of appender file
	* @param file_buff file content/buff
	* @return 0 for success, != 0 for error (error no)
	*/
	public int modify_file(String group_name, String appender_filename, 
			long file_offset, byte[] file_buff) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.modify_file(group_name, appender_filename, 
				file_offset, file_buff);
	}
	
	/**
	* modify appender file to storage server (by file buff)
	* @param group_name the group name of appender file
	* @param appender_filename the appender filename
	* @param file_offset the offset of appender file
	* @param file_buff file content/buff
	* @param buffer_offset start offset of the buff
	* @param buffer_length the length of buff to modify
	* @return 0 for success, != 0 for error (error no)
	*/
	public int modify_file(String group_name, String appender_filename, 
	       long file_offset, byte[] file_buff, int buffer_offset, int buffer_length) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.modify_file(group_name, appender_filename, 
			       file_offset, file_buff, buffer_offset, buffer_length);
	}

	/**
	* modify appender file to storage server (by callback)
	* @param group_name the group name to modify file to
	* @param appender_filename the appender filename
	* @param file_offset the offset of appender file
	* @param modify_size the modify size
	* @param callback the write data callback object
	* @return 0 for success, != 0 for error (error no)
	*/
	public int modify_file(String group_name, String appender_filename, 
	       long file_offset, long modify_size, UploadCallback callback) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.modify_file(group_name, appender_filename, 
			       file_offset, modify_size, callback);
	}
		
	/**
	* delete file from storage server
	* @param group_name the group name of storage server
	*	@param remote_filename filename on storage server
	* @return 0 for success, none zero for fail (error code)
	*/
	public int delete_file(String group_name, String remote_filename) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.delete_file(group_name, remote_filename);
	}
	
	/**
	* truncate appender file to size 0 from storage server
	* @param group_name the group name of storage server
	*	@param appender_filename the appender filename
	* @return 0 for success, none zero for fail (error code)
	*/
	public int truncate_file(String group_name, String appender_filename) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.truncate_file(group_name, appender_filename);
	}
	
	/**
	* truncate appender file from storage server
	* @param group_name the group name of storage server
	*	@param appender_filename the appender filename
	* @param truncated_file_size truncated file size
	* @return 0 for success, none zero for fail (error code)
	*/
	public int truncate_file(String group_name, String appender_filename, 
			long truncated_file_size) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.truncate_file(group_name, appender_filename, truncated_file_size);
	}
	
	/**
	* download file from storage server
	* @param group_name the group name of storage server
	*	@param remote_filename filename on storage server
	* @return file content/buff, return null if fail
	*/
	public byte[] download_file(String group_name, String remote_filename) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.download_file(group_name, remote_filename);
	}
	
	/**
	* download file from storage server
	* @param group_name the group name of storage server
	*	@param remote_filename filename on storage server
	* @param file_offset the start offset of the file
	* @param download_bytes download bytes, 0 for remain bytes from offset
	* @return file content/buff, return null if fail
	*/
	public byte[] download_file(String group_name, String remote_filename, 
			long file_offset, long download_bytes) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.download_file(group_name, remote_filename, 
				file_offset, download_bytes);
	}

	/**
	* download file from storage server
	* @param group_name the group name of storage server
	*	@param remote_filename filename on storage server
	* @param local_filename  filename on local
	* @return 0 success, return none zero errno if fail
	*/
	public int download_file(String group_name, String remote_filename, 
	                  String local_filename) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.download_file(group_name, remote_filename, local_filename);
	}
	
	/**
	* download file from storage server
	* @param group_name the group name of storage server
	*	@param remote_filename filename on storage server
  * @param file_offset the start offset of the file
	* @param download_bytes download bytes, 0 for remain bytes from offset
	* @param local_filename  filename on local
	* @return 0 success, return none zero errno if fail
	*/
	public int download_file(String group_name, String remote_filename, 
	                  long file_offset, long download_bytes, 
	                  String local_filename) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.download_file(group_name, remote_filename, 
                file_offset, download_bytes, local_filename);
	}
	
	/**
	* download file from storage server
	* @param group_name the group name of storage server
	*	@param remote_filename filename on storage server
	* @param callback call callback.recv() when data arrive
	* @return 0 success, return none zero errno if fail
	*/
	public int download_file(String group_name, String remote_filename, 
	                  DownloadCallback callback) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.download_file(group_name, remote_filename, callback);
	}
	
	/**
	* download file from storage server
	* @param group_name the group name of storage server
	*	@param remote_filename filename on storage server
  * @param file_offset the start offset of the file
	* @param download_bytes download bytes, 0 for remain bytes from offset
	* @param callback call callback.recv() when data arrive
	* @return 0 success, return none zero errno if fail
	*/
	public int download_file(String group_name, String remote_filename, 
	                  long file_offset, long download_bytes, 
	                  DownloadCallback callback) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.download_file(group_name, remote_filename, 
                file_offset, download_bytes, callback);
	}
	
	/**
	* get all metadata items from storage server
	* @param group_name the group name of storage server
	*	@param remote_filename filename on storage server
	* @return meta info array, return null if fail
	*/
	public NameValuePair[] get_metadata(String group_name, String remote_filename)throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.get_metadata(group_name, remote_filename);
	}
	
	/**
	* set metadata items to storage server
	* @param group_name the group name of storage server
	*	@param remote_filename filename on storage server
	*	@param meta_list meta item array
	* @param op_flag flag, can be one of following values: <br>
	*            <ul><li> ProtoCommon.STORAGE_SET_METADATA_FLAG_OVERWRITE: overwrite all old
	*				       metadata items</li></ul>
	*            <ul><li> ProtoCommon.STORAGE_SET_METADATA_FLAG_MERGE: merge, insert when
	*				       the metadata item not exist, otherwise update it</li></ul>
	* @return 0 for success, !=0 fail (error code)
	*/
	public int set_metadata(String group_name, String remote_filename, 
							NameValuePair[] meta_list, byte op_flag) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.set_metadata(group_name, remote_filename, 
				meta_list, op_flag);
	}

	/**
	* get file info decoded from the filename, fetch from the storage if necessary
	* @param group_name the group name
	*	@param remote_filename the filename
	* @return FileInfo object for success, return null for fail
	*/
	public FileInfo get_file_info(String group_name, String remote_filename) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.get_file_info(group_name, remote_filename);
	}

	/**
	* get file info from storage server
	* @param group_name the group name of storage server
	*	@param remote_filename filename on storage server
	* @return FileInfo object for success, return null for fail
	*/
	public FileInfo query_file_info(String group_name, String remote_filename) throws IOException, MyException
	{
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		return storageClient.query_file_info(group_name, remote_filename);
	}

	public StorageServer getStorageServer() {
		return storageServer;
	}

	public void setStorageServer(StorageServer storageServer) {
		this.storageServer = storageServer;
	}

	public TrackerClient getTracker() {
		return tracker;
	}

	public TrackerServer getTrackerServer() {
		return trackerServer;
	}

	public JfdfsPool getDataSource() {
		return dataSource;
	}
	
	
}
