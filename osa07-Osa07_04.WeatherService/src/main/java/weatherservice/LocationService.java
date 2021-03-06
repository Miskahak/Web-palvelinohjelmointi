/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    
    @Autowired
    private LocationRepository locationRepository;
    
    @Cacheable("locations")
    public List<Location> findAll(){
        return locationRepository.findAll();
    }
    @Cacheable("locations")
    public Location getOne(Long id) {
        return locationRepository.getOne(id);
    }
    
    @CacheEvict(cacheNames={"locations"}, allEntries=true)
    public void evict(){
}
}