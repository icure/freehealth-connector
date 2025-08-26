package be.ehealth.business.mycarenetcommons.v3.mapper;

import be.ehealth.business.mycarenetdomaincommons.domain.CareProvider;
import be.ehealth.business.mycarenetdomaincommons.domain.CommonInput;
import be.ehealth.business.mycarenetdomaincommons.domain.Identification;
import be.ehealth.business.mycarenetdomaincommons.domain.Nihii;
import be.ehealth.business.mycarenetdomaincommons.domain.Origin;
import be.ehealth.business.mycarenetdomaincommons.domain.Party;
import be.ehealth.technicalconnector.config.util.domain.PackageInfo;
import be.fgov.ehealth.mycarenet.commons.core.v3.CareProviderType;
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType;
import be.fgov.ehealth.mycarenet.commons.core.v3.IdType;
import be.fgov.ehealth.mycarenet.commons.core.v3.NihiiType;
import be.fgov.ehealth.mycarenet.commons.core.v3.OriginType;
import be.fgov.ehealth.mycarenet.commons.core.v3.PackageType;
import be.fgov.ehealth.mycarenet.commons.core.v3.PartyType;
import be.fgov.ehealth.mycarenet.commons.core.v3.ValueRefString;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper
public abstract class CommonInputMapper {
   public CommonInputMapper() {
   }

   @Mapping(
      source = "test",
      target = "request.isTest"
   )
   public abstract CommonInputType map(CommonInput var1);

   @Mappings({@Mapping(
   source = "packageInfo",
   target = "package"
), @Mapping(
   source = "siteId",
   target = "siteID.value"
)})
   public abstract OriginType map(Origin var1);

   @Mappings({@Mapping(
   source = "packageName",
   target = "name.value"
), @Mapping(
   source = "userName",
   target = "license.username"
), @Mapping(
   source = "password",
   target = "license.password"
)})
   public abstract PackageType map(PackageInfo var1);

   public abstract CareProviderType map(CareProvider var1);

   @Mapping(
      source = "value",
      target = "value.value"
   )
   public abstract NihiiType map(Nihii var1);

   @Mappings({@Mapping(
   source = "name",
   target = "name.value"
), @Mapping(
   source = "nihii",
   target = "nihii"
), @Mapping(
   source = "ssin",
   target = "ssin.value"
), @Mapping(
   source = "cbe",
   target = "cbe.value"
)})
   public abstract IdType map(Identification var1);

   public abstract PartyType map(Party var1);

   @AfterMapping
   public IdType doAfterMapping(@MappingTarget IdType idType) {
      if (idType != null && idType.getCbe() != null && idType.getCbe().getValue() == null) {
         idType.setCbe((ValueRefString)null);
      }

      if (idType != null && idType.getSsin() != null && idType.getSsin().getValue() == null) {
         idType.setSsin((ValueRefString)null);
      }

      if (idType != null && idType.getName() != null && idType.getName().getValue() == null) {
         idType.setName((ValueRefString)null);
      }

      return idType;
   }

   @AfterMapping
   public NihiiType doAfterMapping(@MappingTarget NihiiType nihiiType) {
      if (nihiiType != null && nihiiType.getValue() != null && nihiiType.getValue().getValue() == null) {
         nihiiType.setValue((ValueRefString)null);
      }

      return nihiiType;
   }

   @AfterMapping
   public OriginType doAfterMapping(@MappingTarget OriginType originType) {
      if (originType != null && originType.getSiteID() != null && originType.getSiteID().getValue() == null) {
         originType.setSiteID((ValueRefString)null);
      }

      return originType;
   }

   @AfterMapping
   public PackageType doAfterMapping(@MappingTarget PackageType packageType) {
      if (packageType != null && packageType.getName() != null && packageType.getName().getValue() == null) {
         packageType.setName((ValueRefString)null);
      }

      return packageType;
   }
}
