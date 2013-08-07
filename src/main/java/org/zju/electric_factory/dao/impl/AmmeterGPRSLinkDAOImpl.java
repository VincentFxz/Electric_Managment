package org.zju.electric_factory.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zju.electric_factory.entity.AmmeterGPRSLink;

@Repository
@Transactional
public class AmmeterGPRSLinkDAOImpl extends HibernateDAO<AmmeterGPRSLink, Long>{

}
