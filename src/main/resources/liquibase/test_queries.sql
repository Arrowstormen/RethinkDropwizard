select d.id, pi.pickup_time, donor.name, d.pickup_info, d.chef_info, fi.name, pi.quarts
FROM donation as d, chef_information as c, pickup_information as pi, donor, food_item as fi
where d.donor = donor.id AND d.pickup_info = pi.id AND d.chef_info = c.id AND fi.id = c.id;