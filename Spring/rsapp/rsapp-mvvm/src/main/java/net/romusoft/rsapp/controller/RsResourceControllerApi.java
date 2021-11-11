package net.romusoft.rsapp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * resource for static
 * @author eromu
 *
 */
@ApiController
public class RsResourceControllerApi extends RsControllerBase {

	private static final int MAX_CACHE_PERIOD = 3600 * 24 * 120; // every 120 days

	/**
	 * end point for js common head tags
	 * @return the js
	 * @throws IOException ioexception
	 */
	@RequestMapping(value = "commonHeadTags")
	public ResponseEntity<byte[]> commonHeadTags() throws IOException {

		try {
			String filepath = "commonHeadTags.js";
			Resource resource = new ClassPathResource(filepath);
			if (resource.exists() == false)
				throw new IOException("commHeadTags file does not exist.");

			InputStream resourceInputStream = resource.getInputStream();
			byte[] data = IOUtils.toByteArray(resourceInputStream);
			CacheControl cache = CacheControl.maxAge(MAX_CACHE_PERIOD, TimeUnit.SECONDS).cachePublic();
			ResponseEntity<byte[]> responseEntity = ResponseEntity.ok().cacheControl(cache).body(data);

			return responseEntity;
		} catch (IOException e) {
			e.printStackTrace();

			throw e;

		}

	}

}
