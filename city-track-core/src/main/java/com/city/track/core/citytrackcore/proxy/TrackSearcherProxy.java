package com.city.track.core.citytrackcore.proxy;

import com.city.track.core.citytrackcore.bean.TrackBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "track-searcher")
@RibbonClient(name="track-searcher")
public interface TrackSearcherProxy {
    @GetMapping("/{repository}/{genre}")
    List<TrackBean> getTracksByGenre(@PathVariable("repository") String repository, @PathVariable("genre") String genre);
}
